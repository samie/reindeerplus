package org.vaadin.reindeerplus.component.list;

import org.vaadin.reindeerplus.component.Span;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

public class NotificationListItem extends ListItem {

    public NotificationListItem(
            String author,
            String activity,
            String linkText,
            Class<? extends Component> navigationTarget,
            String time
    ) {
        setPrefix(new Avatar(author));
        setPrimary(new Span(
                new Span(author, FontWeight.SEMIBOLD),
                new Span(" " + activity + " ", TextColor.SECONDARY),
                new RouterLink(linkText, navigationTarget)
        ));
        setSecondary(time);
    }

}
