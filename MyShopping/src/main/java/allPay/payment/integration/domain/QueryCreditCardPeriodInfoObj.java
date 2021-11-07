package allPay.payment.integration.domain;

/**
 * 信用卡定期定額訂單查詢物件
 * @author mark.chiu
 *
 */
public class QueryCreditCardPeriodInfoObj {
	
	/**
	 * MerchantID
	 * 會員編號
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * 會員交易編號
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * TimeStamp
	 * 驗證時間
	 */
	private String TimeStamp = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * 取得MerchantID 會員編號
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * 設定MerchantID 會員編號
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * 取得MerchantTradeNo 會員交易編號，訂單產生時傳送給O’Pay的會員交易編號。
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	/**
	 * 設定MerchantTradeNo 會員交易編號，訂單產生時傳送給O’Pay的會員交易編號。
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * 取得TimeStamp 驗證時間
	 * @return TimeStamp
	 */
	public String getTimeStamp() {
		return TimeStamp;
	}
	/**
	 * 設定TimeStamp 驗證時間
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		TimeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "QueryCreditCardPeriodInfoObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo
				+ ", TimeStamp=" + TimeStamp + "]";
	}
}