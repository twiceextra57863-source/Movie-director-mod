package com.sdmine.recorder.project;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PROJECT_DIR =
            Path.of("action-recorder", "projects");

    public static void init() throws IOException {
        Files.createDirectories(PROJECT_DIR);
    }

    public static void save(ProjectData project) throws IOException {
        File file = PROJECT_DIR.resolve(project.name + ".json").toFile();
        try (Writer writer = new FileWriter(file)) {
            GSON.toJson(project, writer);
        }
    }

    public static List<ProjectData> loadAll() throws IOException {
        List<ProjectData> list = new ArrayList<>();

        File[] files = PROJECT_DIR.toFile().listFiles((d, n) -> n.endsWith(".json"));
        if (files == null) return list;

        for (File file : files) {
            try (Reader reader = new FileReader(file)) {
                list.add(GSON.fromJson(reader, ProjectData.class));
            }
        }
        return list;
    }
}

