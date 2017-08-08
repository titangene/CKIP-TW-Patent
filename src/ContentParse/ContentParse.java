package ContentParse;

import ckip.CKIP_Operations;
import tw.cheyingwu.ckip.CKIP;
import tw.cheyingwu.ckip.Term;

public class ContentParse {
	private CKIP_Operations ckip_operations;
	private StringBuilder tokenizationString = new StringBuilder();
	private CKIP ckip;
	
	private String content = "床台裝飾架之部分 PART OF A DECORATIVE FRAME FOR BEDSTEAD";
	private String tag_str, term_str, term_tag_str;
	
	public ContentParse() {
		ckip_operations = new CKIP_Operations();
		ckip = ckip_operations.CKIP_Send(content);
		
		int lineCount = 0;
		
		for (Term term : ckip.getTerm()) {
			tag_str = term.getTag();
			term_str = term.getTerm();
			term_str = tag_str.equals("FW") ? term.getTerm().toLowerCase() : term.getTerm();
			
			term_tag_str = term_str + "(" + tag_str + ") ";
			tokenizationString.append(term_tag_str);
			
			if (tag_str != null && tag_str.contains("CATEGORY")) {
				System.out.println(tokenizationString);
				tokenizationString = new StringBuilder();
				lineCount++;
			}
		}
		
		if (lineCount == 0) {
			System.out.println(tokenizationString);
		}
		System.out.println("End");
	}
}