package markup;

import java.util.List;

public class Strong extends AbstractMarkedText implements ToParagraph {

    public Strong(List<ToParagraph> text) {
        super(text);
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        completeMarkdown(builder, "__");
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        completeBBCode(builder, "[b]", "[/b]");
    }
}
