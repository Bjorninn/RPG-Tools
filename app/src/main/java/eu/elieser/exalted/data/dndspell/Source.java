package eu.elieser.exalted.data.dndspell;

/**
 * Created by bjorn on 15/05/16.
 */
public class Source
{
    private String book;
    private Integer page;

    public String getBook()
    {
        return book;
    }

    public void setBook(String book)
    {
        this.book = book;
    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    @Override
    public String toString()
    {
        return "Source{" +
                "book='" + book + '\'' +
                ", page=" + page +
                '}';
    }
}
