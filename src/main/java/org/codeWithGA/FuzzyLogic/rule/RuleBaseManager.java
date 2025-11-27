package org.codeWithGA.FuzzyLogic.rule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;


public class RuleBaseManager {
    private final List<FuzzyRule> rules = new ArrayList<>();

    public RuleBaseManager() {}

    public void addRule(FuzzyRule rule) {
        rules.add(rule);
    }

    public boolean removeRuleById(String id) {
        Optional<FuzzyRule> r = findById(id);
        if (r.isPresent()) {
            rules.remove(r.get());
            return true;
        }
        return false;
    }

    public FuzzyRule removeRuleAt(int index) {
        return rules.remove(index);
    }

    public boolean updateRule(String id, FuzzyRule updated) {
        for (int i = 0; i < rules.size(); i++) {
            if (Objects.equals(rules.get(i).getId(), id)) {
                rules.set(i, updated);
                return true;
            }
        }
        return false;
    }

    public Optional<FuzzyRule> findById(String id) {
        return rules.stream().filter(r -> Objects.equals(r.getId(), id)).findFirst();
    }

    public List<FuzzyRule> getRules() {
        return Collections.unmodifiableList(new ArrayList<>(rules));
    }

    public List<FuzzyRule> getEnabledRules() {
        return rules.stream().filter(FuzzyRule::isEnabled).collect(Collectors.toList());
    }

    public boolean enableRule(String id) {
        Optional<FuzzyRule> opt = findById(id);
        opt.ifPresent(r -> r.enable());
        return opt.isPresent();
    }

    public boolean disableRule(String id) {
        Optional<FuzzyRule> opt = findById(id);
        opt.ifPresent(r -> r.disable());
        return opt.isPresent();
    }

    public boolean setRuleWeight(String id, double weight) {
        Optional<FuzzyRule> opt = findById(id);
        if (opt.isPresent()) {
            opt.get().setWeight(weight);
            return true;
        }
        return false;
    }

    public boolean setRuleAntecedents(String id, List<Antecedent> newAntecedents) {
        Optional<FuzzyRule> opt = findById(id);
        if (opt.isPresent()) {
            opt.get().setAntecedents(newAntecedents);
            return true;
        }
        return false;
    }

    public boolean setRuleConsequent(String id, Consequent newConsequent) {
        Optional<FuzzyRule> opt = findById(id);
        if (opt.isPresent()) {
            opt.get().setConsequent(newConsequent);
            return true;
        }
        return false;
    }

    public void saveToFile(File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer w = new FileWriter(file)) {
            gson.toJson(this.rules, w);
        }
    }

    public void saveToFile(String path) throws IOException {
        saveToFile(new File(path));
    }

    public void loadFromFile(File file) throws IOException {
        Gson gson = new Gson();
        try (Reader r = new FileReader(file)) {
            Type listType = new TypeToken<List<FuzzyRule>>() {}.getType();
            List<FuzzyRule> loaded = gson.fromJson(r, listType);
            rules.clear();
            if (loaded != null) {
                rules.addAll(loaded);
            }
        }
    }

    public void loadFromFile(String path) throws IOException {
        loadFromFile(new File(path));
    }


    public String toJsonString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this.rules);
    }

    // Convenience: load from JSON string
    public void loadFromJsonString(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<FuzzyRule>>() {}.getType();
        List<FuzzyRule> loaded = gson.fromJson(json, listType);
        rules.clear();
        if (loaded != null) rules.addAll(loaded);
    }

    // Helper: get a map of id -> rule for quick lookup
    public Map<String, FuzzyRule> getRuleMap() {
        return Collections.unmodifiableMap(rules.stream().collect(Collectors.toMap(FuzzyRule::getId, r -> r)));
    }
}
