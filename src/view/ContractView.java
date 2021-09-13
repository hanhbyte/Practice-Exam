package view;

import controller.ContractController;
import model.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContractView {
    public static void main(String[] args) {
        Validate validate = Validate.getInstance();
        ContractController contractController = new ContractController();
        contractController.readInFile();
        String chose;
        String regexChose = "^[1-8]+";
        boolean checkOut;
        Scanner inputNumber = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);
        do {
            System.out.println("---CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ");
            System.out.println("Chọn chức năng theo số (để tiếp tục):");
            System.out.println("1. Xem danh sách.");
            System.out.println("2. Thêm mới.");
            System.out.println("3. Cập nhật.");
            System.out.println("4. Xóa.");
            System.out.println("5. Tìm kiếm.");
            System.out.println("6. Đọc từ file.");
            System.out.println("7. Ghi vào file.");
            System.out.println("8. Thoát.");
            System.out.println("Chọn chức năng:");
            chose = inputNumber.nextLine();
            checkOut = validate.validate(chose, regexChose) & chose.equals("8");
            switch (chose){
                case "1":
                    showAllList(contractController);
                    break;
                case "2":
                    addContract(contractController);
                    break;
                case "3":
                    updateContract(contractController);
                    break;
                case "4":
                    deleteContract(contractController);
                    break;
                case "5":
                    searchComtractByPhoneNumber(contractController);
                    break;
                case "6":
                    readFile(contractController);
                    break;
                case "7":
                    writeFile(contractController);
                    break;
                case "8":
                    break;
                default:
                    System.out.println("Nhập sai chức năng, hãy nhập lại");
                    break;
            }
        }while (!checkOut);
    }

    public static void showAllList(ContractController contractController) {
        contractController.showAllContract();
        showContract(contractController);
    }

    public static void showContract(ContractController contractController) {
        Scanner inputNumber = new Scanner(System.in);
        Scanner inputString = new Scanner(System.in);
        System.out.println("Nhập số điện thoại bạn muốn xem thông tin: ");
        String phoneNumber = inputNumber.nextLine();
        int index = contractController.findContract(phoneNumber);
        if (index != -1) {
            Contract contract = contractController.getList().get(index);
            String number = contract.getPhoneNumber();
            String part = contract.getPartContract();
            String name = contract.getName();
            String sex = contract.getSex();
            String address = contract.getAddress();
            List<String> listInfo = new ArrayList<>();
            listInfo.add(number);
            listInfo.add(part);
            listInfo.add(name);
            listInfo.add(sex);
            listInfo.add(address);
            for (int i = 0; i < listInfo.size(); i++) {
                System.out.println(listInfo.get(i));
                inputString.nextLine();
            }
        } else {
            System.out.println("Không tìm thấy!");
        }
    }

    public static Contract createContract() {
        Scanner inputString = new Scanner(System.in);
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = inputString.nextLine();
        System.out.println("Nhập tên: ");
        String name = inputString.nextLine();
        System.out.println("Nhập giới tính: ");
        String sex = inputString.nextLine();
        System.out.println("Nhập địa chỉ: ");
        String address = inputString.nextLine();
        System.out.println("Nhập nhóm danh bạ: ");
        String part = inputString.nextLine();
        if (phoneNumber.equals("") || name.equals("") || sex.equals("") || address.equals("") || part.equals("")) {
            System.out.println("Nhập lại: ");
            createContract();
        }
        return new Contract(phoneNumber, part, name, sex, address);
    }

    public static void addContract(ContractController contractController) {
        Contract contract = createContract();
        contractController.addContract(contract);
    }

    public static void updateContract(ContractController contractController) {
        Scanner inputString = new Scanner(System.in);
        System.out.println("Nhập số điện thoại cần sửa: ");
        String phoneNumber = inputString.nextLine();
        int index = contractController.findContract(phoneNumber);
        if (index != -1) {
            System.out.println("Nhập tên: ");
            String name = inputString.nextLine();
            System.out.println("Nhập giới tính: ");
            String sex = inputString.nextLine();
            System.out.println("Nhập địa chỉ: ");
            String address = inputString.nextLine();
            System.out.println("Nhập nhóm danh bạ: ");
            String part = inputString.nextLine();
            Contract contract = new Contract(phoneNumber, part, name, sex, address);
            contractController.editContract(phoneNumber, contract);
        }
    }

    public static void deleteContract(ContractController contractController) {
        Scanner inputString = new Scanner(System.in);
        System.out.println("Nhập số điện thoại cần xóa: ");
        String phoneNumber = inputString.nextLine();
        contractController.deleteContract(phoneNumber);
    }

    public static void searchComtractByPhoneNumber(ContractController contractController) {
        Scanner inputString = new Scanner(System.in);
        System.out.println("Nhập số điện thoại cần xóa: ");
        String phoneNumber = inputString.nextLine();
        int index = contractController.findContract(phoneNumber);
        if (index == -1) {
            System.out.println("Không tìm thấy!");
        } else {
            System.out.println("Thông tin số điện thoại là: " + contractController.getList().get(index));
        }
    }

    public static void writeFile(ContractController contractController) {
        contractController.writeOnFile();
    }

    public static void readFile(ContractController contractController) {
        contractController.readInFile();
    }
}

