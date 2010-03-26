package name.pehl.piriti.sample.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author $Author$
 * @version $Date$ $Revision$
 */
public class BooksGenerator
{
    public List<Book> generateBooks()
    {
        List<Book> books = new ArrayList<Book>();

        Author johnIrving = new Author("John", "Irving");
        Book hotelNewHampshire = new Book("978-0345417954", "The Hotel New Hampshire");
        hotelNewHampshire.setPages(432);
        hotelNewHampshire.setAuthor(johnIrving);
        hotelNewHampshire.addReview("A hectic gaudy saga with the verve of a Marx Brothers movie.");
        hotelNewHampshire
                .addReview("Rejoice! John Irving has written another book according to your world. You must read this book.");
        hotelNewHampshire.addReview("Spellbinding, intensely human, a high-wire act of dazzling virtuosity.");
        books.add(hotelNewHampshire);

        Author douglasAdams = new Author("Douglas", "Adams");
        Book hitchhikersGuideToTheGalaxy = new Book("978-1400052929", "The Hitchhikers Guide to the Galaxy");
        hitchhikersGuideToTheGalaxy.setPages(224);
        hitchhikersGuideToTheGalaxy.setAuthor(douglasAdams);
        hitchhikersGuideToTheGalaxy
                .addReview("Don't panic! Here are words of praise for The Hitchhiker's Guide to the Galaxy!");
        hitchhikersGuideToTheGalaxy
                .addReview("It's science fiction and it's extremely funny...inspired lunacy that leaves hardly a science fiction cliche alive.");
        hitchhikersGuideToTheGalaxy
                .addReview("Very simply, the book is one of the funniest SF spoofs ever written, with hyperbolic ideas folding in on themselves.");
        hitchhikersGuideToTheGalaxy
                .addReview("As parody, it's marvelous: It contains just about every science fiction cliche you can think of. As humor, it's, well, hysterical.");
        books.add(hitchhikersGuideToTheGalaxy);

        Author patrickSueskind = new Author("Patrick", "Süskind");
        Book perfume = new Book("978-0307277763", "Perfume");
        perfume.setPages(225);
        perfume.setAuthor(patrickSueskind);
        perfume.addReview("A fable of crimial genius.... Remarkable.");
        perfume.addReview("Mesmerizing from first page to last.... A highly sophisticated horror tale.");
        perfume
                .addReview("A supremely accomplished work of art, marvelously crafted and enjoyable and rich in historical detail.");
        perfume.addReview("An ingenious story...about a most exotic monster.... Suspense build up steadily.");
        books.add(perfume);
        return books;
    }
}
