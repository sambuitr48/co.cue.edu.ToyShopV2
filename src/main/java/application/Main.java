package application;

import connection.DataBaseConnection;
import mapping.dto.ClientDTO;
import mapping.dto.EmployeeDTO;
import mapping.dto.OrderDTO;
import mapping.dto.ToyDTO;
import models.Client;
import models.Employee;
import models.Toy;
import repository.impl.Detail.DetailRepositoryJDBCImpl;
import repository.impl.client.ClientRepositoryJDBCImpl;
import repository.impl.employee.EmployeeRepositoryJDBImpl;
import repository.impl.order.OrderRepositoryJDBCImpl;
import repository.impl.toy.ToyRepositoryJDBCImpl;
import services.impl.toy.ToyServiceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

public class Main {
    private static ToyServiceImpl toyStore;
    private static ClientRepositoryJDBCImpl clientRepo;
    private static DetailRepositoryJDBCImpl detailRepo;
    private static OrderRepositoryJDBCImpl orderRepo;
    private static EmployeeRepositoryJDBImpl employeeRepo;
    private static ToyRepositoryJDBCImpl toyRepo;

    private static void searchToy() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del juguete a buscar: ");
        int id = scanner.nextInt();
        ToyDTO toy = toyStore.search(id);
        if (toy != null) {
            System.out.println("Juguete encontrado:");
            System.out.println(toy);
        } else {
            System.out.println("No se encontró ningún juguete con el ID proporcionado.");
        }
    }
    public static OrderDTO addOrder(Scanner scanner) {
        System.out.println("Enter the order ID:");
        int order_id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        // Request information about the client associated with the order
        System.out.println("Enter the ID of the associated client:");
        int client_id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        Client client = ClientHandler.getClientById(client_id);

        // Request information about the employee associated with the order
        System.out.println("Enter the ID of the associated employee:");
        int employee_id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        Employee employee = EmployeeHandler.getEmployeeById(employee_id);

        System.out.println("Enter the purchase date (in yyyy-MM-dd HH:mm:ss format):");
        String purchase_date_str = scanner.nextLine();
        // Convert the string to a LocalDateTime object
        LocalDateTime purchase_date = LocalDateTime.parse(purchase_date_str);

        return new OrderDTO(order_id, client, employee, purchase_date);
    }

    private static void addClient(Scanner scanner) throws SQLException {
        System.out.println("Ingrese el nombre del cliente");
        String name = scanner.nextLine();
        System.out.println("Ingrese el ID number del usuario");
        int cedula = scanner.nextInt();
        System.out.println("Ingrese la fecha de nacimiento del cliente");
        Date age = Date.valueOf(scanner.nextLine());
        ClientDTO clienteDTO = new ClientDTO(cedula,name,age);
        toyStore.addClient(clienteDTO);
    }
    private static void updateStock(Scanner scanner) {
        System.out.print("Ingrese el ID del juguete para actualizar el stock: ");
        int toyId = scanner.nextInt();
        System.out.print("Ingrese la cantidad de cambio de stock (positivo para agregar, negativo para restar): ");
        int quantityChange = scanner.nextInt();
        toyStore.updateStock(toyId, quantityChange);
        System.out.println("Stock actualizado con éxito.");
    }
    private static void addNewToy(Scanner scanner) throws SQLException {
        int id = 0;
        System.out.print("Ingrese el nombre del juguete: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el precio del juguete: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Ingrese la cantidad en stock del juguete: ");
        int stock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese la categoría del juguete: ");
        String category = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingrese el tipo de la categoría del juguete");
        String type = scanner.nextLine();
        ToyDTO toyDTO = new ToyDTO(id,name,category,price,stock);
        toyStore.addToy(toyDTO);
        System.out.println("Juguete agregado con éxito.");
    }

    public static void main(String[] args) throws SQLException {
        try(Connection conn = DataBaseConnection.getInstance()){
            toyStore = new ToyServiceImpl();
            clientRepo = new ClientRepositoryJDBCImpl();
            detailRepo = new DetailRepositoryJDBCImpl();
            employeeRepo = new EmployeeRepositoryJDBImpl();
            orderRepo = new OrderRepositoryJDBCImpl();
            toyRepo = new ToyRepositoryJDBCImpl();

            Scanner scanner = new Scanner(System.in);
            int option = 123;
            while (option !=0){
                System.out.println("Welcome to Kira's ToyStore");
                System.out.println("1. List toys");//
                System.out.println("2. Search toy by ID");//
                System.out.println("3. Add a new toy");//
                System.out.println("4. Update toy");
                System.out.println("5. List details");//
                System.out.println("6. List Employees");//
                System.out.println("7. List Clients");//
                System.out.println("8. List order");
                System.out.println("9. New Order");
                System.out.println("10. New employee");
                System.out.println("11. New client");//
                System.out.println("0. Exit");//
                System.out.print("Write your option: ");

                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            CompletableFuture<List<ToyDTO>> future = CompletableFuture.supplyAsync(() -> {
                                List<ToyDTO> list = toyStore.listToys();
                                if (!list.isEmpty()) {
                                    for (ToyDTO toys : list) {
                                        System.out.println(toys);
                                        System.out.println("Loading...");
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    System.out.println("There are no toys on the list");
                                }
                                return list;
                            });
                            future.join();
                            System.out.println("The task is completed");
                            break;
                        case 2:
                            searchToy();
                            break;
                        case 3:
                            addNewToy(scanner);
                            break;
                        case 4:

                            break;
                        case 5:

                            break;
                        case 6:
                            CompletableFuture<List<ClientDTO>> future = CompletableFuture.supplyAsync(() -> {
                                List<ClientDTO> list = toyStore.listClients();
                                if (!list.isEmpty()) {
                                    for (ClientDTO clientDTO : list) {
                                        System.out.println(clientDTO);
                                        System.out.println("Loading...");
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    System.out.println("There are no toys on the list");
                                }
                                return list;
                            });
                            future.join();
                            System.out.println("The task is completed");
                            break;
                        case 7:
                            CompletableFuture<List<EmployeeDTO>> future = CompletableFuture.supplyAsync(() -> {
                                List<EmployeeDTO> list = toyStore.listEmployees();
                                if (!list.isEmpty()) {
                                    for (EmployeeDTO employeeDTO : list) {
                                        System.out.println(employeeDTO);
                                        System.out.println("Loading...");
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } else {
                                    System.out.println("There are no toys on the list");
                                }
                                return list;
                            });
                            future.join();
                            System.out.println("The task is completed");
                            break;
                        case 8:
                            break;
                        case 9:
                            addOrder(scanner);
                            break;
                        case 10:
                            break;
                        case 11:
                            addClient(scanner);
                            break;
                        case 0:
                            break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); {}
            }
        }
        }catch (SQLException e) {
            e.printStackTrace();}}}
