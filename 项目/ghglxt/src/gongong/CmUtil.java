package gongong;

public class CmUtil {
	private static long pkId; // 唯一Long型主键
	synchronized public static long getPkId() {
		long lTmp = System.currentTimeMillis();
		if (pkId < lTmp)
			pkId = lTmp;
		else
			pkId++;
		return pkId;
	}
	
	public static void main(String[] args){
		System.out.println(CmUtil.getPkId());
	}
}
