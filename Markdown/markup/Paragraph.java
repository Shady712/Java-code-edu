package markup;

import java.util.LinkedList;
import java.util.List;

public class Paragraph extends AbstractMarkedText implements ToList {

    public Paragraph(List<ToParagraph> text) {
        super(text);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        completeMarkdown(builder, "");
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        completeBBCode(builder, "", "");
    }
}
