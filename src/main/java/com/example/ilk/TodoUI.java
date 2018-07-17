package com.example.ilk;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


@SpringUI
public class TodoUI extends UI {
	
	private VerticalLayout root;
	private HorizontalLayout formLayout;
	private TextField task;
	private Button add;
	
	@Autowired
	TodoLayout todoLayout;
	

	@Override
	protected void init(VaadinRequest request) {
		setupLayout();
		addHeader();
		addForm();
		addToList();
		addDeleteButton();
		
	}

	private void setupLayout() {
		
		root=new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(root);
	}

	private void addHeader() {
		
		Label header =new Label("TODOs");
		header.addStyleName(ValoTheme.LABEL_H1);
		root.addComponent(header);
	}

	private void addForm() {
		
		formLayout=new HorizontalLayout();
		formLayout.setWidth("80%");
		task=new TextField();
		add=new Button("");
		add.addStyleNames(ValoTheme.BUTTON_PRIMARY);
		add.setIcon(VaadinIcons.PLUS);
		formLayout.addComponentsAndExpand(task);
		formLayout.addComponents(add);
		
		add.addClickListener(click->{
			todoLayout.add(new Todo(task.getValue()));
			task.clear();
			task.focus();
		});
		task.focus();
		add.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		
		root.addComponent(formLayout);
	}
	
	private void addToList() {
		
		todoLayout.setWidth("80%");
		root.addComponent(todoLayout);
		
	}

	private void addDeleteButton() {
	
		root.addComponent(new Button("Delete completed", click->  {
			todoLayout.deleteCompleted();
			
		}));
	}

}
