package com.stjean.alpha;

import java.util.*;
import java.util.regex.Pattern;

public class UtilisateurService {

    public static final List<Utilisateur> users = new ArrayList<>();

    private static final Pattern EMAIL_RE =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static class EmailInvalidException extends RuntimeException {
        public EmailInvalidException(String m) { super(m); }
    }
    public static class SuppressionInvalidException extends RuntimeException {
        public SuppressionInvalidException(String m) { super(m); }
    }
    public static class NegativeGeneralBalanceException extends RuntimeException {
        public NegativeGeneralBalanceException(String m) { super(m); }
    }

    public void ajouter(Utilisateur user) {
        if (user == null) throw new NullPointerException("user null");
        if (user.getEmail() == null || !EMAIL_RE.matcher(user.getEmail()).matches())
            throw new EmailInvalidException("Email invalide");
        users.add(user);
    }

    public boolean supprimer(int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        if (!removed) throw new SuppressionInvalidException("Utilisateur inexistant: " + id);
        return true;
    }

    public List<Utilisateur> lister() {
        return Collections.unmodifiableList(users);
    }

    public Optional<Utilisateur> afficher(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    // 6a) Solde général
    public double analyseSoldeGeneral() {
        double total = users.stream().mapToDouble(Utilisateur::getSoldePersonnel).sum();
        if (total < 0) throw new NegativeGeneralBalanceException("Solde général négatif: " + total);
        return total;
    }

    // 6c) Utilisateur le plus riche
    public Optional<Utilisateur> utilisateurLePlusRiche() {
        return users.stream().max(Comparator.comparingDouble(Utilisateur::getSoldePersonnel));
    }
}
