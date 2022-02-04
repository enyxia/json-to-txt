package fr.alkanife.jsontotxt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.alkanife.botcommons.Utils;
import net.dv8tion.jda.api.JDA;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Characters {

    private static ArrayList<Character> characters = new ArrayList<>();
    private static ArrayList<String> failed = new ArrayList<>();

    public static boolean load() {
        failed = new ArrayList<>();
        characters = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(Utils.absolutePath() + "/characters/"))) {
            paths.filter(Files::isRegularFile).forEach(path -> {

                try {
                    if (!path.getFileName().toString().startsWith(".")) {

                        System.out.println("Reading " + path.getFileName());

                        String raw = Files.readString(path);

                        Gson gson = new GsonBuilder().serializeNulls().create();

                        Character character = gson.fromJson(raw, Character.class);

                        characters.add(character);
                    }

                } catch (Exception exception) {
                    failed.add(path.getFileName() + "");
                }
            });
        } catch (Exception exception) {
            return false;
        }

        return true;
    }

    public static Character search(String name) {
        Character found = null;

        for (Character character : characters)
            if (character.getFull_name().toLowerCase(java.util.Locale.ROOT).contains(name.toLowerCase(java.util.Locale.ROOT)))
                found = character;

        return found;
    }

    public static List<Character> searchByAuthor(String name) {
        List<Character> foundCharacters = new ArrayList<>();

        for (Character character : characters)
            if (character.getOriginal_owner().toLowerCase(java.util.Locale.ROOT).contains(name.toLowerCase(java.util.Locale.ROOT)))
                foundCharacters.add(character);

        return foundCharacters;
    }

    public static ArrayList<Character> getCharacters() {
        return characters;
    }

    public static ArrayList<String> getFailed() {
        return failed;
    }
}
