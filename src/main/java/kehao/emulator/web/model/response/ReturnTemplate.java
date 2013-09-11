package kehao.emulator.web.model.response;

public class ReturnTemplate<T> {
  private int returnCode;
  private String returnMsg;
  private T returnObjs;

  public int getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(int returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }

  public T getReturnObjs() {
    return returnObjs;
  }

  public void setReturnObjs(T returnObjs) {
    this.returnObjs = returnObjs;
  }

  public boolean badRequest() {
    return returnCode != 0 && (returnMsg == null || !returnMsg.equals("No error."));
  }
}
