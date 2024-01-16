package sw.biz.enums;

/**
 * Enum - 单据类型
 */
public enum BillTypeEnum {
	PO("采购订单",1,"PO"),
	AO("到货单", 2, "AO"),
	SO("销售订单",3,"SO"),
	IO("其他入库单",4,"IO"),
	OO("其他出库单",5,"OO"),
	PI("采购入库",6,"PI"),
	SOT("销售出库",7,"SOT");

	/** 名称 */
	private String sign;
	/** 值 */
	private int value;
	/** 编码 */
	private String code;

	BillTypeEnum(String sign, int value, String code) {
		this.sign = sign;
		this.value = value;
		this.code = code;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
