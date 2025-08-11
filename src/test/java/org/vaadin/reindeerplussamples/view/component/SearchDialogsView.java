package org.vaadin.reindeerplussamples.view.component;

import org.vaadin.reindeerplus.component.MaterialSymbol;
import org.vaadin.reindeerplussamples.dialog.SearchDialog;
import org.vaadin.reindeerplussamples.view.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("Search dialogs")
@Route(value = "search-dialogs", layout = MainLayout.class)
public class SearchDialogsView extends ComponentView {

    public SearchDialogsView() {
        addClassNames(AlignItems.START, Padding.Top.LARGE);

        createBasicExample();
        createPaddedExample();
        createPreviewExample();
    }

    private void createBasicExample() {
        SearchDialog dialog = new SearchDialog();

        Button button = new Button("Basic example", e -> dialog.open());
        button.setPrefixComponent(MaterialSymbol.SEARCH.create());
        add(button);
    }

    private void createPaddedExample() {
        SearchDialog dialog = new SearchDialog();
        dialog.setPadding(true);

        Button button = new Button("Padded example", e -> dialog.open());
        button.setPrefixComponent(MaterialSymbol.SEARCH.create());
        add(button);
    }

    private void createPreviewExample() {
        SearchDialog dialog = new SearchDialog();
        dialog.setPadding(true);
        dialog.setPreview(true);
        dialog.setWidth(800, Unit.PIXELS);

        Button button = new Button("Preview example", e -> dialog.open());
        button.setPrefixComponent(MaterialSymbol.SEARCH.create());
        add(button);
    }

}
