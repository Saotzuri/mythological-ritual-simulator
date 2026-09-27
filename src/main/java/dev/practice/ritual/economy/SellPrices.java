package dev.practice.ritual.economy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public final class SellPrices {
    public record Entry(String id, String display, Material material, ItemStack itemStack, long price) {}

    private static final Map<String, Entry> BY_ID = new LinkedHashMap<>();

    static {
        add("GRIFFIN_FEATHER", "Griffin Feather", Material.FEATHER, 200_000);
        add("BRAIDED_GRIFFIN_FEATHER", "Braided Griffin Feather", Material.FEATHER, 40_000_000);
        add("CHIMERA", "Enchanted Book (Chimera 1)", Material.ENCHANTED_BOOK, 30_000_000);
        add("MANTICORE", "Manti-core", Material.BLAZE_ROD, 30_000_000);
        add("STINGER", "Fateful Stinger", Material.ARROW, 5_000_000);
        add("CRETAN_URN", "Cretan Urn", skull("895df42056d79602a6915fec6ef804bbe57b450a0a484ce9997403d0c904d356"), 250_000);
        add("SHELMET", "Dwarf Turtle Shelmet", skull("bac1510610d50443fd97f34a6e94e21d0b93d5c7529f3d95c1816ce24a13dc84"), 250_000);
        add("PLUSHIE", "Crochet Tiger Plushie", skull("f66fb4d9e6ae0c5e61ec6b9131e015fb89792852e195e96864662ba422ef26e2"), 250_000);
        add("REMEDIES", "Antique Remedies", Material.AZURE_BLUET, 250_000);
        add("MYTHOS_FRAGMENT", "Mythos Fragment", skull("7c5e2923b44d7c8fe725735c3415d40cfc95b8d70df115f5633551ecb1ae65e4"), 25_000);
        add("HILT", "Hilt of Revelations", Material.STICK, 150_000);
        add("SHIMMERING_WOOL", "Shimmering Wool", Material.YELLOW_WOOL, 50_000_000);
        add("CROWN", "Crown of Greed", Material.GOLDEN_HELMET, 1_000_000);
        add("DAEDALUS_STICK", "Daedalus Stick", Material.STICK, 2_500_000);
        add("MINOS_RELIC", "Minos Relic", skull("40b4648cbd817c7b5fc654c9c054e360d81bbfe1a00f214657a174e3e0d07d21"), 30_000_000);
        add("BRAIN_FOOD", "Brain Food", skull("867e27a256cdb7b741dbfe8419f5f3d89bdfbd52a1b3f0fdb9c95edeeafc1f3f"), 2_000_000);
        add("SOUVENIR", "Washed-up Souvenir", skull("3777f04644dec5f80bfeaa7401acfbbc150eb25d3ff8be4220e7c34426cd727c"), 250_000);
        add("MANTICORE", "Manti-core", skull("d4f1eb29bf8314703394c624c5832e0dfbce8fa6c25870205d67d96ed743bf90"), 30_000_000);
        add("STINGER", "Fateful Stinger", skull("4e2c26ad88fdd10381284650770d9b59d3688a52868b3bcc7762f88a34303de8"), 5_000_000);
        add("ANCIENT_CLAW", "Ancient Claw", Material.FLINT, 500);
        add("ENCHANTED_ANCIENT_CLAW", "Enchanted Ancient Claw", Material.FLINT, 80_000);
        add("ENCHANTED_GOLD", "Enchanted Gold Ingot", Material.GOLD_INGOT, 1_200);
        add("ENCHANTED_GOLD_BLOCK", "Enchanted Gold Block", Material.GOLD_BLOCK, 192_000);
    }

    private static void add(String id, String display, Material mat, long price) {
        BY_ID.put(id, new Entry(id, display, mat, null, price));
    }

    private static void add(String id, String display, ItemStack item, long price) {
        BY_ID.put(id, new Entry(id, display, item.getType(), item, price));
    }

    private static ItemStack skull(String textureHash) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        String texture = java.util.Base64.getEncoder().encodeToString(
                ("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + textureHash + "\"}}}")
                        .getBytes(StandardCharsets.UTF_8));
        item.editMeta(meta -> {
            SkullMeta skull = (SkullMeta) meta;
            var profile = Bukkit.createProfile(UUID.randomUUID());
            profile.setProperty(new com.destroystokyo.paper.profile.ProfileProperty("textures", texture));
            skull.setPlayerProfile(profile);
        });
        return item;
    }

    public static Entry byId(String id) {
        return BY_ID.get(id);
    }

    public static Entry byDisplay(String name) {
        if (name == null) return null;
        String n = name.replaceAll("§[0-9a-fk-orx]", "").trim().toLowerCase(Locale.ROOT);
        for (Entry e : BY_ID.values()) {
            if (e.display.toLowerCase(Locale.ROOT).equals(n)) return e;
        }
        if (n.contains("chimera")) return BY_ID.get("CHIMERA");
        if (n.contains("braided")) return BY_ID.get("BRAIDED_GRIFFIN_FEATHER");
        if (n.contains("griffin feather")) return BY_ID.get("GRIFFIN_FEATHER");
        if (n.contains("manti")) return BY_ID.get("MANTICORE");
        if (n.contains("stinger")) return BY_ID.get("STINGER");
        if (n.contains("shelmet")) return BY_ID.get("SHELMET");
        if (n.contains("plushie")) return BY_ID.get("PLUSHIE");
        if (n.contains("remed")) return BY_ID.get("REMEDIES");
        if (n.contains("urn")) return BY_ID.get("CRETAN_URN");
        if (n.contains("hilt")) return BY_ID.get("HILT");
        if (n.contains("wool")) return BY_ID.get("SHIMMERING_WOOL");
        if (n.contains("crown")) return BY_ID.get("CROWN");
        if (n.contains("daedalus stick")) return BY_ID.get("DAEDALUS_STICK");
        if (n.contains("relic")) return BY_ID.get("MINOS_RELIC");
        if (n.contains("brain")) return BY_ID.get("BRAIN_FOOD");
        if (n.contains("souvenir")) return BY_ID.get("SOUVENIR");
        if (n.contains("mythos")) return BY_ID.get("MYTHOS_FRAGMENT");
        if (n.contains("enchanted ancient")) return BY_ID.get("ENCHANTED_ANCIENT_CLAW");
        if (n.contains("ancient claw")) return BY_ID.get("ANCIENT_CLAW");
        if (n.contains("enchanted gold block")) return BY_ID.get("ENCHANTED_GOLD_BLOCK");
        if (n.contains("enchanted gold")) return BY_ID.get("ENCHANTED_GOLD");
        return null;
    }

    public static Map<String, Entry> all() {
        return BY_ID;
    }

    public static String coins(long n) {
        return String.format(Locale.ROOT, "%,d", n);
    }
}
