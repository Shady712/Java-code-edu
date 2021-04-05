package markup;

import java.util.List;

public class Emphasis extends AbstractMarkedText implements ToParagraph {

    public Emphasis(List<ToParagraph> text) {
        super(text);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        completeMarkdown(builder, "*");
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        completeBBCode(builder, "[i]", "[/i]");
    }
}
