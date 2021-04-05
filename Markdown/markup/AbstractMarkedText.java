package markup;

import java.util.List;

public abstract class AbstractMarkedText implements Elem {
    protected List<?extends Elem> list;

    protected AbstractMarkedText(List<?extends Elem> text) {
        list = text;
    }

    protected void completeMarkdown(StringBuilder builder, String tag) {
        builder.append(tag);
        for (Elem element : list) {
            element.toMarkdown(builder);
        }
        builder.append(tag);
    }

    protected void completeBBCode(StringBuilder builder, String tag1, String tag2) {
        builder.append(tag1);
        for (Elem element : list) {
            element.toBBCode(builder);
        }
        builder.append(tag2);
    }
}
