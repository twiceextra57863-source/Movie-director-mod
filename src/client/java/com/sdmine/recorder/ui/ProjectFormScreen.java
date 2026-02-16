package com.sdmine.recorder.ui;

import com.sdmine.recorder.project.ProjectData;
import com.sdmine.recorder.project.ProjectManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class ProjectFormScreen extends Screen {

    private TextFieldWidget nameField;
    private TextFieldWidget fpsField;

    private String quality = "High";
    private int selectedTemplate = 0;

    private final String[] templates = {
            "None", "Basic", "Action", "Cinematic"
    };

    public ProjectFormScreen() {
        super(Text.literal("Create New Project"));
    }

    @Override
    protected void init() {
        int y = 40;

        nameField = new TextFieldWidget(
                textRenderer, 20, y, 200, 20, Text.literal("Name"));
        addDrawableChild(nameField);

        y += 30;

        fpsField = new TextFieldWidget(
                textRenderer, 20, y, 200, 20, Text.literal("FPS"));
        fpsField.setText("30");
        addDrawableChild(fpsField);

        y += 30;

        addDrawableChild(
                ButtonWidget.builder(Text.literal("Quality: " + quality), btn -> {
                    quality = switch (quality) {
                        case "High" -> "Medium";
                        case "Medium" -> "Low";
                        default -> "High";
                    };
                    btn.setMessage(Text.literal("Quality: " + quality));
                }).dimensions(20, y, 140, 20).build()
        );

        y += 30;

        addDrawableChild(
                ButtonWidget.builder(Text.literal("Template: " + templates[selectedTemplate]), btn -> {
                    selectedTemplate = (selectedTemplate + 1) % templates.length;
                    btn.setMessage(Text.literal("Template: " + templates[selectedTemplate]));
                }).dimensions(20, y, 160, 20).build()
        );

        y += 40;

        addDrawableChild(
                ButtonWidget.builder(Text.literal("Create Project"), btn -> {
                    ProjectData project = new ProjectData(nameField.getText());
                    project.fps = Integer.parseInt(fpsField.getText());
                    project.quality = quality;
                    project.template = templates[selectedTemplate];

                    ProjectManager.save(project);
                    client.setScreen(null);
                }).dimensions(20, y, 160, 20).build()
        );
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
