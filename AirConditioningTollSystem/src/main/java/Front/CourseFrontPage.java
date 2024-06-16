package Front;

public class CourseFrontPage {
	public static int pageSize=12;
	public static int TotalPage=0;
	public static StringBuffer ShowInfo(int pageNum,int pageRows){
		TotalPage = pageRows%pageSize==0?pageRows/pageSize:pageRows/pageSize+1;
		StringBuffer sb = new StringBuffer();
		sb.append("<table style='width: 100%;'>");
		sb.append("<tr>");
		sb.append("<td style='width:25%;text-align:center;'><a style='color:black;' onclick='prvePage();'>上一页</a></td>");
		sb.append("<td style='width:25%;text-align:center;'><span>第</span><span id='pageNum'>"+pageNum+"</span><span>页</span><span>/共</span><span>"+TotalPage+"</span><span>页</span></td>");
		sb.append("<td style='width:25%;text-align:center;'><a style='color:black;' onclick='nextPage();'>下一页</a></td>");
		sb.append("<td style='width:25%;text-align:center;'><span>共</span><span>"+pageRows+"</span><span>行</span></td>");
		sb.append("</tr>");
		sb.append("</table>");
		return sb;
	}

}
