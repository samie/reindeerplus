package org.vaadin.reindeerplus.component.list;

import org.vaadin.reindeerplus.component.Badge;
import org.vaadin.reindeerplus.component.Layout;
import org.vaadin.reindeerplus.component.Span;
import org.vaadin.reindeerplus.utility.BadgeVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

public class TaskListItem extends ThreeLineListItem {

    public TaskListItem(String name, String status, String content, String date) {
        setGap(Layout.Gap.SMALL);

        Paragraph paragraph = new Paragraph(content);
        paragraph.addClassNames(Margin.Vertical.NONE);

        setPrimary(
                new Span(name, FontWeight.SEMIBOLD),
                new Badge(status, BadgeVariant.PILL, BadgeVariant.SMALL)
        );
        setSecondary(date);
        setContent(content);
        setLineClamp(Layout.LineClamp.LINE_CLAMP_2);
    }

}
