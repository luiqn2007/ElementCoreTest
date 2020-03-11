package com.example.examplemod;

import com.elementtimes.elementcore.api.annotation.ModBlock;
import com.example.examplemod.block.Blocks;
import net.minecraft.block.Block;
import net.minecraft.util.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Auto {

    private static Path resource;

    public static void main(String[] args) {
        File file = new File("D:\\System\\Users\\lq2007\\Desktop\\book\\Screenshot_20200227_204710_com.tencent.weread.jpg");
    }

    public static void maina(String[] args) {
        try {
            resource = Paths.get(Auto.class.getResource("").toURI());
            while (!"out".equals(resource.getFileName().toString())) {
                resource = resource.getParent();
            }
            resource = resource.resolveSibling("src/main/resources/assets/examplemod/");
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void run() throws Exception {
        appendBlockLang();
    }

    private static void appendBlockLang() throws IOException {
        String[] names = Arrays.stream(Blocks.class.getFields())
                .filter(f -> Block.class.isAssignableFrom(f.getType()))
                .filter(f -> Modifier.isStatic(f.getModifiers()))
                .filter(f -> f.isAnnotationPresent(ModBlock.class))
                .map(f -> ImmutablePair.of(f.getName(), f.getAnnotation(ModBlock.class)))
                .map(pair -> ifEmpty(pair.getRight().unlocalizedName(), pair.getLeft()))
                .map(name -> "tile.examplemod." + name + ".name=")
                .toArray(String[]::new);
        Path lang = resource.resolve("lang/en_us.lang");
        ensureFile(lang);
        List<String> writeTo = new ArrayList<>();
        List<String> contains = Files.readAllLines(lang);
        for (String name : names) {
            boolean skip = false;
            name = name.toLowerCase();
            for (String string : contains) {
                if (string.startsWith(name)) {
                    skip = true;
                    contains.remove(string);
                    break;
                }
            }
            if (skip) {
                continue;
            }
            writeTo.add(name);
        }
        if (!writeTo.isEmpty()) {
            Files.write(lang, writeTo, StandardOpenOption.APPEND);
        }
    }

    private static void createTeBlockStateJson() throws IOException {
        Path teBlockStates = resource.resolve("blockstates");
        Path teTextures = resource.resolve("textures/blocks/te");

        for (File texture : Objects.requireNonNull(teTextures.toFile().listFiles())) {
            String name = texture.getName();
            name = name.substring(0, name.lastIndexOf('.'));
            Path jsonFile = teBlockStates.resolve(name + ".json");
            if (ensureFile(jsonFile)) {
                String file = "{\n" +
                        "  \"forge_marker\": 1,\n" +
                        "  \"defaults\": {\n" +
                        "    \"model\": \"cube_all\",\n" +
                        "    \"textures\": {\n" +
                        "      \"all\": \"examplemod:blocks/te/" + name + "\"\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"variants\": {\n" +
                        "    \"normal\": [{}],\n" +
                        "    \"inventory\": [{}]\n" +
                        "  }\n" +
                        "}";
                Files.write(jsonFile, file.getBytes(), StandardOpenOption.WRITE);
            }
        }
    }

    private static String ifEmpty(String check, String orElse) {
        return StringUtils.isNullOrEmpty(check) ? orElse : check;
    }

    private static boolean ensureFile(Path file) throws IOException {
        if (Files.notExists(file)) {
            Files.createFile(file);
            return true;
        }
        return false;
    }
}
