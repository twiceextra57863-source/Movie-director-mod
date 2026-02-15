package com.sdmine.recorder.ui;

import com.sdmine.recorder.project.ProjectData;
import com.sdmine.recorder.project.ProjectManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ProjectFormScreen extends Screen {

    private String name = "";
    private String fps = "30";
    private String quality = "High";
    private int selectedTemplate = 0;

    private final String[] templates = {
            "None",
            "Basic",
            "Action",
            "Cinematic"
    };

    public ProjectFormScreen() {
        super(Text.literal("Create New Project"));
    }

    @Override
    protected void init() {

        int y = 40;

        // Name input
        this.addDrawableChild(
                new TextFieldWidget(
                        this.textRenderer, 20, y, 200, 20,
                        Text.literal("Name")
                ) {
                    @Override
                    public void write(String text) {
                        name += text;
                    }
                }
        );

        y += 30;

        // FPS input
        this.addDrawableChild(
                new TextFieldWidget(
                        this.textRenderer, 20, y, 200, 20,
                        Text.literal("FPS")
                ) {
                    @Override
                    public void write(String text) {
                        fps += text;
                    }
                }
        );

        y += 30;

        // Quality buttons
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Quality: " + quality), btn -> {
                    // toggle
                    quality = quality.equals("High") ? "Medium" : quality.equals("Medium") ? "Low" : "High";
                }).dimensions(20, y, 120, 20).build()
        );

        y += 30;

        // Template buttons
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Template: " + templates[selectedTemplate]), btn -> {
                    selectedTemplate = (selectedTemplate + 1) % templates.length;
                }).dimensions(20, y, 140, 20).build()
        );

        y += 40;

        // Create button
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Create Project"), btn -> {

                    ProjectData project = new ProjectData(name);
                    project.fps = Integer.parseInt(fps);
                    project.quality = quality;
                    project.template = templates[selectedTemplate];

                    try {
                        ProjectManager.save(project);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    client.setScreen(null);

                }).dimensions(20, y, 140, 20).build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}

