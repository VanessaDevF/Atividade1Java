import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

// Classe que representa um usuário
class User {
    String name;
    String email;
    String document;

    User(String name, String email, String document) {
        this.name = name;
        this.email = email;
        this.document = document;
    }

    public String toString() {
        return name + " (" + email + ") - " + document;
    }
}

// Classe que representa um evento
class Event {
    String name;
    String address;
    String category;
    LocalDateTime dateTime;
    String description;

    Event(String name, String address, String category, LocalDateTime dateTime, String description) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.dateTime = dateTime;
        this.description = description;
    }

    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return name + " | " + address + " | " + category + " | " + dateTime.format(fmt) + " | " + description;
    }
}

// Classe principal do sistema de eventos
public class Main {
    static Scanner sc = new Scanner(System.in);
    static List<Event> events = new ArrayList<>();
    static User user;

    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Eventos ===");
        cadastrarUsuario();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1) Cadastrar evento");
            System.out.println("2) Listar eventos");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            if (op == 1) cadastrarEvento();
            else if (op == 2) listarEventos();
            else if (op == 0) {
                salvarEventos();
                System.out.println("Saindo... eventos salvos em events.dat");
                break;
            }
            else System.out.println("Opção inválida.");
        }
    }

    // Realiza o cadastro do usuário
    static void cadastrarUsuario() {
        System.out.println("Cadastro de usuário:");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Documento: ");
        String doc = sc.nextLine();
        user = new User(nome, email, doc);
        System.out.println("Usuário cadastrado: " + user);
    }

    // Realiza o cadastro de um novo evento
    static void cadastrarEvento() {
        System.out.println("\nNovo evento:");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String end = sc.nextLine();
        System.out.print("Categoria: ");
        String cat = sc.nextLine();
        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String dataStr = sc.nextLine();
        LocalDateTime dt = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        System.out.print("Descrição: ");
        String desc = sc.nextLine();

        Event e = new Event(nome, end, cat, dt, desc);
        events.add(e);
        System.out.println("Evento cadastrado!");
    }

    // Lista todos os eventos cadastrados
    static void listarEventos() {
        System.out.println("\nEventos cadastrados:");
        if (events.isEmpty()) {
            System.out.println("(nenhum evento)");
        } else {
            for (Event e : events) {
                System.out.println(e);
            }
        }
    }

    // Salva os eventos em arquivo
    static void salvarEventos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("events.dat"))) {
            for (Event e : events) {
                pw.println(e);
            }
        } catch (Exception ex) {
            System.out.println("Erro ao salvar eventos: " + ex.getMessage());
        }
    }
}
