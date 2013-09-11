package kehao.ui.widget;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kehao.emulator.game.model.basic.*;
import kehao.ui.AppFrame;
import kehao.util.AssetsOracle;
import org.apache.commons.lang3.StringUtils;

public abstract class DynamicCardGroupEditor extends JFrame {

  public static String BEFORE_BATTLE = "战斗前卡组";
  public static String AFTER_BATTLE = "战斗后卡组";

  private static String COULD_NOT_SAVE = "无法保存";

  private DynamicCardGroupEditor me = this;

  private ReadOnlyGrid cardGrid = new ReadOnlyGrid(new String[]{"编号", "卡牌", "等级", "进化技能"});
  private ReadOnlyGrid runeGrid = new ReadOnlyGrid(new String[]{"编号", "符文", "等级"});
  private JPanel cardEditorPanel;
  private TitledBorder cardPanelBorder = BorderFactory.createTitledBorder("卡牌");
  private Map<Integer, JButton> cardSlots = new HashMap<>();
  private long[] cards = new long[10];
  private int currentCardSlot = -1;
  private Map<Integer, JButton> runeSlots = new HashMap<>();
  private int currentRuneSlot = -1;
  private long[] runes = new long[4];

  private AssetsOracle oracle;
  private String username;
  private String savedCardGroup;
  private Map<Long, UserCard> userCardMap = new HashMap<>();
  private Map<Long, UserRune> userRuneMap = new HashMap<>();
  private int cost = 0;
  private int leadership;

  public DynamicCardGroupEditor(String key, String type, AssetsOracle oracle, String username, List<UserCard> userCards, List<UserRune> userRunes, int leadership) {
    super(username + " - " + key + " - " + type);
    setIconImage(AppFrame.FAVICON);
    this.oracle = oracle;
    this.username = username;
    for(UserCard userCard : userCards) {
      userCardMap.put(userCard.getUserCardId(), userCard);
    }
    for(UserRune userRune : userRunes) {
      userRuneMap.put(userRune.getUserRuneId(), userRune);
    }
    this.leadership = leadership;
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(1000, 600));

    JPanel candidatePanel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;
    constraints.weighty = 3;
    initUserCardTable(userCards);
    candidatePanel.add(new JScrollPane(cardGrid), constraints);
    constraints.gridy = 1;
    constraints.weighty = 1;
    initUserRuneTable(userRunes);
    candidatePanel.add(new JScrollPane(runeGrid), constraints);
    add(candidatePanel, BorderLayout.WEST);

    JPanel editorPanel = new JPanel(new GridBagLayout());
    constraints.gridy = 0;
    constraints.weighty = 2;
    editorPanel.add(createCardEditorPanel(), constraints);
    constraints.gridy = 1;
    constraints.weighty = 1;
    editorPanel.add(createRuneEditorPanel(), constraints);
    JPanel buttonsPanel = new JPanel(new FlowLayout());
    JButton save = new JButton("保存");
    save.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Set<Long> cardIds = new LinkedHashSet<>();
        for(long card : cards) {
          if(card < 1) continue;
          if(!cardIds.add(card)) {
            JOptionPane.showMessageDialog(me, "不能在不同的位置放同一张卡牌：" + cardName(card), COULD_NOT_SAVE, JOptionPane.WARNING_MESSAGE);
            return;
          }
        }
        Set<Long> runeIds = new LinkedHashSet<>();
        Set<Integer> runeDefIds = new HashSet<>();
        for(long rune : runes) {
          if(rune < 1) continue;
          int runeDefId = runeDefId(rune);
          if(runeDefId < 1) {
            JOptionPane.showMessageDialog(me, "无效符文：" + rune, COULD_NOT_SAVE, JOptionPane.WARNING_MESSAGE);
            return;
          }
          if(!runeDefIds.add(runeDefId)) {
            JOptionPane.showMessageDialog(me, "不能使用相同的符文：" + runeName(rune), COULD_NOT_SAVE, JOptionPane.WARNING_MESSAGE);
            return;
          }
          if(!runeIds.add(rune)) {
            JOptionPane.showMessageDialog(me, "不能在不同的位置放同一张符文：" + runeName(rune), COULD_NOT_SAVE, JOptionPane.WARNING_MESSAGE);
            return;
          }
        }
        if(cardIds.isEmpty()) {
          if(runeIds.isEmpty()) {
            savedCardGroup = "";
          } else {
            JOptionPane.showMessageDialog(me, "不能使用只有符文的卡组", COULD_NOT_SAVE, JOptionPane.WARNING_MESSAGE);
          }
        } else {
          savedCardGroup = StringUtils.join(cardIds, "_") + ";" + StringUtils.join(runeIds, "_");
        }
      }
    });
    buttonsPanel.add(save);
    JButton clear = new JButton("撤销");
    clear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        unsetCardSlots();
        unsetRuneSlots();
      }
    });
    buttonsPanel.add(clear);
    JButton close = new JButton("关闭");
    close.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    buttonsPanel.add(close);
    constraints.gridy = 2;
    constraints.weighty = 0;
    editorPanel.add(buttonsPanel, constraints);
    add(editorPanel, BorderLayout.CENTER);
    loadSetting();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosed(WindowEvent e) {
        onClose();
      }
    });

    pack();
    setLocationRelativeTo(AppFrame.LOCATION);
    setVisible(true);
  }

  private int cardDefId(long userCardId) {
    UserCard userCard = userCardMap.get(userCardId);
    if(userCard == null) return -1;
    return userCard.getCardId();
  }

  private String cardName(long userCardId) {
    int defId = cardDefId(userCardId);
    CardDef cardDef = oracle.getCardDef(username, defId);
    if(cardDef == null) return "未知卡牌" + defId;
    return cardDef.getCardName();
  }

  private int cardCost(long userCardId) {
    UserCard userCard = userCardMap.get(userCardId);
    if(userCard == null) return -1;
    int defId = userCard.getCardId();
    CardDef cardDef = oracle.getCardDef(username, defId);
    if(cardDef == null) return -1;
    if(userCard.evolved()) return cardDef.getEvoCost();
    return cardDef.getCost();
  }

  private int runeDefId(long userRuneId) {
    UserRune userRune = userRuneMap.get(userRuneId);
    if(userRune == null) return -1;
    return userRune.getRuneId();
  }

  private String runeName(long userRuneId) {
    int defId = runeDefId(userRuneId);
    RuneDef rune = oracle.getRuneDef(username, defId);
    if(rune == null) return "未知卡牌" + defId;
    return rune.getRuneName();
  }

  private void loadSetting() {
    savedCardGroup = loadDynamicCardGroup();
    if(savedCardGroup.isEmpty()) return;
    String[] setting = savedCardGroup.split(";");
    String[] cardIds = setting[0].split("_");
    int index = 0;
    for(String cardId : cardIds) {
      if(cardId.isEmpty()) continue;
      cards[index++] = Long.parseLong(cardId);
    }
    if(setting.length > 1) {
      String[] runeIds = setting[1].split("_");
      index = 0;
      for(String runeId : runeIds) {
        if(runeId.isEmpty()) continue;
        runes[index++] = Long.parseLong(runeId);
      }
    }
    refreshSlotButtons();
  }

  private void calculateCost() {
    cost = 0;
    for(long card : cards) {
      if(card < 1) continue;
      cost += cardCost(card);
    }
  }

  private void updateCost() {
    calculateCost();
    cardPanelBorder.setTitle("卡牌（Cost：" + cost + "/" + leadership + "）");
    if(cost > leadership) {
      cardPanelBorder.setTitleColor(Color.red);
    } else {
      cardPanelBorder.setTitleColor(null);
    }
//    if(isVisible()) cardEditorPanel.repaint();
  }

  private void onClose() {
    setDynamicCardGroup(savedCardGroup);
  }

  private void refreshSlotButtons() {
    for(int i = 0; i < 10; i++) {
      setCardSlot(i);
    }
    for(int i = 0; i < 4; i++) {
      setRuneSlot(i);
    }
  }

  private void setCardSlot(int slot) {
    JButton slotButton = cardSlots.get(slot);
    if(slotButton != null) {
      long id = cards[slot];
      UserCard userCard = userCardMap.get(id);
      if(userCard != null) {
        CardDef cardDef = oracle.getCardDef(username, userCard.getCardId());
        SkillDef skillDef = oracle.getSkillDef(username, userCard.getSkillNew());
        if(skillDef == null) {
          slotButton.setText("<html><center>" + star(cardDef.getColor()) + "<br />" + cardDef.getCardName() + "<br />LV" + userCard.getLevel() + "<br />（未进化）</center></html>");
        } else {
          slotButton.setText("<html><center>" + star(cardDef.getColor()) + "<br />" + cardDef.getCardName() + "<br />LV" + userCard.getLevel() + "<br />（" + skillDef.getName() + "）</center></html>");
        }
      }
      updateCost();
    }
  }

  private void setCardSlotActive(int slot, boolean active) {
    JButton slotButton = cardSlots.get(slot);
    if(active) {
      slotButton.setText("<html><center><br />卡牌 " + (slot + 1) + "<br />（点击取消）</center><br /></html>");
      slotButton.setForeground(Color.blue);
    } else {
      if(cards[slot] == -1) slotButton.setText("<html><center><br />卡牌 " + (slot + 1) + "<br />（点击选择）</center><br /></html>");
      slotButton.setForeground(null);
    }
  }

  private void unsetCardSlot(int slot, boolean active) {
    JButton slotButton = cardSlots.get(slot);
    if(slotButton != null) {
      cards[slot] = -1L;
      if(active) {
        setCardSlotActive(slot, true);
        JButton old = cardSlots.get(currentCardSlot);
        if(old != null) {
          setCardSlotActive(currentCardSlot, false);
        }
        currentCardSlot = slot;
        cardGrid.setEnabled(true);
      } else {
        setCardSlotActive(slot, false);
        currentCardSlot = -1;
        cardGrid.setEnabled(false);
      }
      updateCost();
    }
  }

  private void unsetCardSlots() {
    for(int i = 0; i < 10; i++) {
      unsetCardSlot(i, false);
    }
  }

  private void setRuneSlotActive(int slot, boolean active) {
    JButton slotButton = runeSlots.get(slot);
    if(active) {
      slotButton.setText("<html><center>符文 " + (slot + 1) + "<br />（点击取消）</center></html>");
      slotButton.setForeground(Color.blue);
    } else {
      if(runes[slot] == -1) slotButton.setText("<html><center>符文 " + (slot + 1) + "<br />（点击选择）</center></html>");
      slotButton.setForeground(null);
    }
  }

  private void unsetRuneSlot(int slot, boolean active) {
    JButton slotButton = runeSlots.get(slot);
    if(slotButton != null) {
      runes[slot] = -1L;
      if(active) {
        setRuneSlotActive(slot, true);
        JButton old = runeSlots.get(currentRuneSlot);
        if(old != null) {
          setRuneSlotActive(currentRuneSlot, false);
        }
        currentRuneSlot = slot;
        runeGrid.setEnabled(true);
      } else {
        setRuneSlotActive(slot, false);
        currentRuneSlot = -1;
        runeGrid.setEnabled(false);
      }
    }
  }

  private void unsetRuneSlots() {
    for(int i = 0; i < 4; i++) {
      unsetRuneSlot(i, false);
    }
  }

  private void setRuneSlot(int slot) {
    JButton slotButton = runeSlots.get(slot);
    if(slotButton != null) {
      long id = runes[slot];
      UserRune userRune = userRuneMap.get(id);
      if(userRune != null) {
        RuneDef runeDef = oracle.getRuneDef(username, userRune.getRuneId());
        slotButton.setText("<html><center>" + star(runeDef.getColor()) + "<br />" + runeDef.getRuneName() + " LV" + userRune.getLevel() + "</center></html>");
      }
    }
  }

  private void initUserCardTable(List<UserCard> userCards) {
    cardGrid.setEnabled(false);
    for(UserCard userCard : userCards) {
      CardDef def = oracle.getCardDef(username, userCard.getCardId());
      SkillDef newSkill = oracle.getSkillDef(username, userCard.getSkillNew());
      cardGrid.addRow(new String[] {Long.toString(userCard.getUserCardId()), def.getColor() + "星 " + def.getCardName(), Integer.toString(userCard.getLevel()), newSkill == null ? "未进化" : newSkill.getName()});
    }

    cardGrid.onSelect(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) return;
        int selection = ((DefaultListSelectionModel)e.getSource()).getMinSelectionIndex();
        if(selection == -1) {
          JButton slot = cardSlots.get(currentCardSlot);
          if(slot != null) {
            slot.setText(Integer.toString(currentCardSlot + 1));
            cards[currentCardSlot] = -1L;
          }
        } else {
          cards[currentCardSlot] = Long.valueOf((String) cardGrid.getValueAt(selection, 0));
          setCardSlot(currentCardSlot);
        }
      }
    });
  }

  private void initUserRuneTable(List<UserRune> userRunes) {
    runeGrid.setEnabled(false);
    for(UserRune userRune : userRunes) {
      RuneDef def = oracle.getRuneDef(username, userRune.getRuneId());
      runeGrid.addRow(new String[] {Long.toString(userRune.getUserRuneId()), def.getRuneName(), Integer.toString(userRune.getLevel())});
    }

    runeGrid.onSelect(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()) return;
        int selection = ((DefaultListSelectionModel)e.getSource()).getMinSelectionIndex();
        if(selection == -1) {
          JButton slot = runeSlots.get(currentRuneSlot);
          if(slot != null) {
            slot.setText(Integer.toString(currentRuneSlot + 1));
            runes[currentRuneSlot] = -1L;
          }
        } else {
          JButton slot = runeSlots.get(currentRuneSlot);
          if(slot != null) {
            runes[currentRuneSlot] = Long.valueOf((String)runeGrid.getValueAt(selection, 0));
            setRuneSlot(currentRuneSlot);
          }
        }
      }
    });
  }

  private JPanel createCardEditorPanel() {
    JPanel ret = new JPanel(new GridLayout(2, 5));
    ret.setBorder(cardPanelBorder);
    int count = 0;
    for(int r = 0; r < 2; r++) {
      for(int c = 0; c < 5; c++) {
        final JButton slot = new JButton();
        final int slotId = count++;
        cardSlots.put(slotId, slot);
        unsetCardSlot(slotId, false);
        slot.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            unsetCardSlot(slotId, currentCardSlot != slotId);
          }
        });
        ret.add(slot);
      }
    }
    cardEditorPanel = ret;
    return ret;
  }

  private JPanel createRuneEditorPanel() {
    JPanel ret = new JPanel(new GridLayout(1, 4));
    ret.setBorder(BorderFactory.createTitledBorder("符文"));
    int count = 0;
    for(int c = 0; c < 4; c++) {
      final JButton slot = new JButton();
      final int slotId = count++;
      runeSlots.put(slotId, slot);
      unsetRuneSlot(slotId, false);
      slot.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          unsetRuneSlot(slotId, currentRuneSlot != slotId);
        }
      });
      ret.add(slot);
    }
    return ret;
  }

  private String star(int num) {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < num; i++) {
      sb.append("★");
    }
    return sb.toString();
  }

  public abstract String loadDynamicCardGroup();

  public abstract void setDynamicCardGroup(String dynamicCardGroup);
}
