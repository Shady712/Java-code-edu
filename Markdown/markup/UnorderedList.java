package markup;

import java.util.List;

public class UnorderedList extends AbstractMarkedText implements ToList {

    public UnorderedList(List<ListItem> text) {
        super(text);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        completeBBCode(builder, "[list]", "[/list]");
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        throw new UnsupportedOperationException();
    }
}
