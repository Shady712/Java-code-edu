package markup;

import java.util.List;

public class OrderedList extends AbstractMarkedText implements ToList {

    public OrderedList(List<ListItem> text) {
        super(text);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        completeBBCode(builder, "[list=1]", "[/list]");
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        throw new UnsupportedOperationException();
    }
}
