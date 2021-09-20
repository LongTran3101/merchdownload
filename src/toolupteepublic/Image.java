package toolupteepublic;

public class Image {
	String Foldername;
	String Imagename;
	String Title;
	String Des;
	String Tag;
	String Main;
	public String getFoldername() {
		return Foldername;
	}
	@Override
	public String toString() {
		return "Image [Foldername=" + Foldername + ", Imagename=" + Imagename + ", Title=" + Title + ", Des=" + Des
				+ ", Tag=" + Tag + ", Main=" + Main + "]";
	}
	public void setFoldername(String foldername) {
		Foldername = foldername;
	}
	public String getImagename() {
		return Imagename;
	}
	public void setImagename(String imagename) {
		Imagename = imagename;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDes() {
		return Des;
	}
	public void setDes(String des) {
		Des = des;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	public String getMain() {
		return Main;
	}
	public void setMain(String main) {
		Main = main;
	}

}
