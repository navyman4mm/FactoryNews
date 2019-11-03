package Models;




public class ArticlesItem{


	private String publishedAt;


	private String author;


	private String urlToImage;


	private String description;


	private String title;


	private String content;


	public ArticlesItem() {
	}

	public ArticlesItem(String publishedAt, String author, String urlToImage, String description, String title,String content) {
		this.publishedAt = publishedAt;
		this.author = author;
		this.urlToImage = urlToImage;
		this.description = description;
		this.title = title;
		this.content = content;
	}

	public void setPublishedAt(String publishedAt){
		this.publishedAt = publishedAt;
	}

	public String getPublishedAt(){
		return publishedAt;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setUrlToImage(String urlToImage){
		this.urlToImage = urlToImage;
	}

	public String getUrlToImage(){
		return urlToImage;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"ArticlesItem{" + 
			"publishedAt = '" + publishedAt + '\'' + 
			",author = '" + author + '\'' + 
			",urlToImage = '" + urlToImage + '\'' + 
			",description = '" + description + '\'' + 
			",title = '" + title + '\'' + 
			",content = '" + content + '\'' +
			"}";
		}
}