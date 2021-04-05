package markup;

import java.util.List;

public class Strikeout extends AbstractMarkedText implements ToParagraph {

    public Strikeout(List<ToParagraph> text) {
        super(text);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        completeMarkdown(builder, "~");
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        completeBBCode(builder, "[s]", "[/s]");
    }
}
