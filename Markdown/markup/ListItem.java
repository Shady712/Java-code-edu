package markup;

import java.util.List;

public class ListItem extends AbstractMarkedText {

    public ListItem(List<ToList> text) {
        super(text);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        completeBBCode(builder, "[*]", "");
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        throw new UnsupportedOperationException();
    }
}
