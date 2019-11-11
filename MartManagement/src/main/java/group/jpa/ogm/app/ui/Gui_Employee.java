package group.jpa.ogm.app.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import group.jpa.ogm.app.controller.client.ClientController;
import group.jpa.ogm.app.entities.Account;
import group.jpa.ogm.app.entities.Customer;
import group.jpa.ogm.app.entities.Employee;
import group.jpa.ogm.app.repository.account.AccountDAO;
import group.jpa.ogm.app.repository.account.AccountDAOImpl;
import group.jpa.ogm.app.repository.employee.EmployeeDAO;
import group.jpa.ogm.app.repository.goods.GoodDAO;
import group.jpa.ogm.app.repository.goods.GoodDAOImpl;
import group.jpa.ogm.app.repository.invoice.InvoiceDAO;
import group.jpa.ogm.app.repository.invoiceDetails.InvoiceDetailsDAO;
import group.jpa.ogm.app.entities.Good;
import group.jpa.ogm.app.entities.Invoice;
import group.jpa.ogm.app.entities.InvoiceDetails;

/**
 * 
 * @author Ngô Tuấn Kiệt
 *
 */

public class Gui_Employee extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/////////////// ///////////////
	private JPasswordField txtmkc, txtmkm, txtnlmkm;
	private JTabbedPane tbpqlcn;

	// Employee

	private JLabel lblId, lblFullName, lblAddress, lblDateOfBirth;
	private JTextField txtId, txtFullName, txtAddress, txtDateOfBirth;

	private JLabel lblOldPass, lblConfirmPass, lblNewPass;
	private JTextField txtOldPass, txtConfirmPass, txtNewPass;

	private JButton btnChangePass, btnCancel;

	// Order

	JTextField txtSearch, txtProductId, txtQuantity, txtPrice;
	JLabel lblSearch, lblName, lblQuantity, lblPrice;
	JButton btnSearch;
	JButton btnAdd, btnRemove, btnModify, btnSave, btnIcon, btnLogout, btnPrintInvoice;

	JComboBox<String> cbbProductName;
	DefaultComboBoxModel<String> modelcbbProductName;

	JLabel lblSumInvoice;
	double sumInvoice;

	///////////////

	private JTabbedPane tbp;

	private JTable table;
	private DefaultTableModel tablemodel;

	private JTable tableGood;
	private DefaultTableModel modelGood;
	/*
	 * private NhanVienNhanBenhDaos nhanVienNhanBenhDaos; private List<BenhNhan>
	 * dsbn; private List<NhanVien> dsnv;
	 */
	private JButton btnHelp;
	private Account account;

	/* Call service */
	static ClientController callService;
	private GoodDAO goodService;
	private InvoiceDetailsDAO invoideDetailsService;
	private AccountDAO accountService;
	private EmployeeDAO employeeService;
	private InvoiceDAO inVoiceService;

	static List<Good> listAllGoods;
	static List<Good> listGoodsBought;

	private JTable tableGoodsBought;
	private DefaultTableModel modelGoodsBought;

	public Gui_Employee(Account account) throws RemoteException, NotBoundException {
		this.account = account;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1100, 700);
		setResizable(true);
		setTitle("Nhân viên bán hàng");

		setIconImage(new ImageIcon("../ima/if_H_sign_hospital_hospital_sign_hospital__medical__road_sign_1887039.png")
				.getImage());


		callService = new ClientController("192.168.31.109", 9999);
		
		goodService = callService.getGoodDAO();
		invoideDetailsService = callService.getInvoiceDetailsDAO();
		accountService = callService.getAccountDAO();
		employeeService = callService.getEmployeeDAO();
		inVoiceService = callService.getInvoiceDAO();

		listAllGoods = new ArrayList<Good>();
		listGoodsBought = new ArrayList<Good>();

		Box bt = Box.createVerticalBox();// CÃ¡i nÃ y lÃ  quáº£n lÃ½ chung cá»§a cáº£ frame
		/**
		 * 
		 */
		////////////////////////////////
		Box b1 = Box.createHorizontalBox();
		b1.setMaximumSize(getMaximumSize());
		b1.add(Box.createHorizontalStrut(20));
		b1.add(new JLabel(new ImageIcon(("../ima/if_cv_job_seeker_employee_unemployee_work_2620524.png"))));
		b1.add(Box.createHorizontalStrut(10));

		System.out.println("path: " + ("../ima/if_cv_job_seeker_employee_unemployee_work_2620524.png"));

		b1.add(Box.createHorizontalStrut(400));
		b1.add(btnLogout = new JButton("Đăng xuất", new ImageIcon(("../ima/if_Logout_105217.png"))));
		bt.add(Box.createVerticalStrut(10));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(btnHelp = new JButton("Hỗ trợ", new ImageIcon(("../ima/if_user_help_1902262.png"))));
		bt.add(b1);
		bt.add(Box.createVerticalStrut(0));
		/////////////////////////////////
		/**
		 * 
		 */
		/////////////////////////////////
		Box bqlbn = Box.createVerticalBox();
		Box bqlbn1 = Box.createHorizontalBox();
		Box bqlbn1_ThongTin = Box.createVerticalBox();
		bqlbn1_ThongTin.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

		Box bqlbn1_ThongTin_IdBN = Box.createHorizontalBox();
		bqlbn1_ThongTin_IdBN.add(new JLabel("Tìm thông tin sảm phẩm: "));
		bqlbn1_ThongTin_IdBN.add(Box.createHorizontalStrut(0));
		bqlbn1_ThongTin_IdBN.add(txtSearch = new JTextField());
		txtSearch.setPreferredSize(getPreferredSize());
		bqlbn1_ThongTin_IdBN.add(Box.createHorizontalStrut(10));
		bqlbn1_ThongTin_IdBN.add(btnSearch = new JButton("Tìm"));

		Box bqlbn1_ThongTin_Ho = Box.createHorizontalBox();
		bqlbn1_ThongTin_Ho.add(new JLabel("Tên sản phẩm: "));
		bqlbn1_ThongTin_Ho.add(Box.createHorizontalStrut(50));
		modelcbbProductName = new DefaultComboBoxModel<String>();
		bqlbn1_ThongTin_Ho.add(cbbProductName = new JComboBox<String>(modelcbbProductName));

		Box bqlbn1_ThongTin_PId = Box.createHorizontalBox();
		bqlbn1_ThongTin_PId.add(new JLabel("Mã sản phẩm: "));
		bqlbn1_ThongTin_PId.add(Box.createHorizontalStrut(55));
		bqlbn1_ThongTin_PId.add(txtProductId = new JTextField());

		// cbbName.setPreferredSize(getPreferredSize());

		Box bqlbn1_ThongTin_Ten = Box.createHorizontalBox();
		bqlbn1_ThongTin_Ten.add(new JLabel("Số lượng: "));
		bqlbn1_ThongTin_Ten.add(Box.createHorizontalStrut(80));
		bqlbn1_ThongTin_Ten.add(txtQuantity = new JTextField());
		// txtQuantity.setPreferredSize(getPreferredSize());

		Box bqlbn1_ThongTin_Sdt = Box.createHorizontalBox();
		bqlbn1_ThongTin_Sdt.add(new JLabel("Giá: "));
		bqlbn1_ThongTin_Sdt.add(Box.createHorizontalStrut(110));
		bqlbn1_ThongTin_Sdt.add(txtPrice = new JTextField());
		// txtPrice.setPreferredSize(getPreferredSize());

		bqlbn1_ThongTin.add(bqlbn1_ThongTin_IdBN);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(5));
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_Ho);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(10));
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_PId);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(10));

		bqlbn1_ThongTin.add(bqlbn1_ThongTin_Ten);
		bqlbn1_ThongTin.add(Box.createVerticalStrut(5));
		bqlbn1_ThongTin.add(bqlbn1_ThongTin_Sdt);

		bqlbn1.add(bqlbn1_ThongTin);
		bqlbn1.add(Box.createHorizontalStrut(10));// Giáº£n cÃ¡ch giá»¯a hai box thÃ´ng tin vÃ  tÃ¬m kiáº¿m
		// bqlbn1.add(bqlbn1_TimKiem_BenhNhan_BacSi);

		Box bqlbn2_ChucNang = Box.createVerticalBox(); // bqlbn2_ChucNang lÃ  quáº£n lÃ½ dÃ²ng cÃ¡c nÃºt chá»©c nÄƒng
		bqlbn2_ChucNang.setBorder(BorderFactory.createTitledBorder("Chức năng"));
		bqlbn2_ChucNang.setMaximumSize(getMaximumSize());
		Box bqlbn2_ChucNang_1 = Box.createHorizontalBox();
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		bqlbn2_ChucNang_1.add(btnAdd = new JButton("Thêm", new ImageIcon(("../ima/if_7_330410.png"))));
		btnAdd.setMaximumSize(getMaximumSize());
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		/*
		 * bqlbn2_ChucNang_1.add(btnModify = new JButton("Cập nhật", new ImageIcon((
		 * "../ima/if_brush-pencil_1055103.png"))));
		 * btnModify.setMaximumSize(getMaximumSize());
		 */
		bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		bqlbn2_ChucNang_1.add(btnRemove = new JButton("Xóa", new ImageIcon(("../ima/if_Save_1493294.png"))));
		btnRemove.setMaximumSize(getMaximumSize());
		/*
		 * bqlbn2_ChucNang_1.add(Box.createHorizontalStrut(10));
		 * bqlbn2_ChucNang_1.add(btnPrintInvoice = new JButton("In hóa đơn", new
		 * ImageIcon(("../ima/if_receipt_3583272.png" ))));
		 * btnPrintInvoice.setMaximumSize(getMaximumSize());
		 */

		bqlbn2_ChucNang.add(bqlbn2_ChucNang_1);

		Box bqlbn3_Danhsach = Box.createVerticalBox(); // bqlbn3_Danhsach lÃ  quáº£n lÃ½ cÃ¡i báº£ng danh sÃ¡ch
		bqlbn3_Danhsach.setBorder(BorderFactory.createTitledBorder("Danh sách mua"));
		Box bqlbn3_Danhsach_1 = Box.createHorizontalBox();
		String[] headers = "Mã sản phẩm; Tên sản phẩm; Số lượng; Giá mỗi sản phẩm; Thành tiền".split(";");
		modelGood = new DefaultTableModel(headers, 0);
		bqlbn3_Danhsach_1.add(new JScrollPane(tableGood = new JTable(modelGood)));
		// tableGood.setDefaultEditor(Object.class, null);
		bqlbn3_Danhsach.add(bqlbn3_Danhsach_1);

		JLabel lblSum;
		Box bqlbn3_Sum = Box.createHorizontalBox();
		bqlbn3_Sum.add(Box.createHorizontalGlue());
		bqlbn3_Sum.add(lblSum = new JLabel("TỔNG THÀNH TIỀN"));
		lblSum.setFont(new Font("Times new roman", Font.PLAIN, 22));
		bqlbn3_Sum.add(Box.createHorizontalStrut(10));
		bqlbn3_Sum.add(lblSumInvoice = new JLabel());
		lblSumInvoice.setFont(new Font("Times new roman", Font.PLAIN, 22));

		bqlbn3_Sum.add(Box.createHorizontalStrut(30));
		bqlbn3_Sum.add(btnPrintInvoice = new JButton("In hóa đơn", new ImageIcon(("../ima/if_receipt_3583272.png"))));
		// btnPrintInvoice.setMaximumSize(getMaximumSize());
		bqlbn3_Danhsach.add(bqlbn3_Sum);

		bqlbn.add(bqlbn1);
		bqlbn.add(bqlbn2_ChucNang);
		bqlbn.add(bqlbn3_Danhsach);
		/////////////////////////////////
		/**
		 * CÃ¡i nÃ y lÃ  quáº£n lÃ½ cÃ¡ nhÃ¢n
		 */
		/////////////////////////////////

		tbpqlcn = new JTabbedPane();

		Box b_BasicInfo, b_ChangePassword;

		b_BasicInfo = Box.createVerticalBox();// b_BasicInfo quáº£n lÃ½ chung cá»§a xem thÃ´ng tin

		Box b_BasicInfo_Id = Box.createHorizontalBox();
		b_BasicInfo_Id.add(Box.createHorizontalStrut(10));
		b_BasicInfo_Id.add(lblId = new JLabel("Mã nhân viên:"));
		lblId.setFont(new Font("Times new roman", Font.PLAIN, 16));
		b_BasicInfo_Id.add(Box.createHorizontalStrut(50));
		b_BasicInfo_Id.add(txtId = new JTextField());
		b_BasicInfo_Id.add(Box.createHorizontalStrut(50));

		Box b_BasicInfo_FullName = Box.createHorizontalBox();
		b_BasicInfo_FullName.add(Box.createHorizontalStrut(10));
		b_BasicInfo_FullName.add(lblFullName = new JLabel("Họ và tên: "));
		lblFullName.setFont(new Font("Times new roman", Font.PLAIN, 16));
		b_BasicInfo_FullName.add(Box.createHorizontalStrut(90));
		b_BasicInfo_FullName.add(txtFullName = new JTextField());
		b_BasicInfo_FullName.add(Box.createHorizontalStrut(50));

		Box b_BasicInfo_DOfBirth = Box.createHorizontalBox();
		b_BasicInfo_DOfBirth.add(Box.createHorizontalStrut(10));
		b_BasicInfo_DOfBirth.add(lblDateOfBirth = new JLabel("Ngày sinh:"));
		lblDateOfBirth.setFont(new Font("Times new roman", Font.PLAIN, 16));
		b_BasicInfo_DOfBirth.add(Box.createHorizontalStrut(90));
		b_BasicInfo_DOfBirth.add(txtDateOfBirth = new JTextField());
		b_BasicInfo_DOfBirth.add(Box.createHorizontalStrut(50));

		Box b_BasicInfo_Address = Box.createHorizontalBox();
		b_BasicInfo_Address.add(Box.createHorizontalStrut(10));
		b_BasicInfo_Address.add(lblAddress = new JLabel("Địa chỉ:"));
		lblAddress.setFont(new Font("Times new roman", Font.PLAIN, 16));
		b_BasicInfo_Address.add(Box.createHorizontalStrut(110));
		b_BasicInfo_Address.add(txtAddress = new JTextField());
		b_BasicInfo_Address.add(Box.createHorizontalStrut(50));

		b_BasicInfo.add(Box.createVerticalStrut(10));
		b_BasicInfo.add(b_BasicInfo_Id);
		b_BasicInfo.add(Box.createVerticalStrut(10));
		b_BasicInfo.add(b_BasicInfo_FullName);

		b_BasicInfo.add(Box.createVerticalStrut(10));
		b_BasicInfo.add(b_BasicInfo_DOfBirth);

		b_BasicInfo.add(Box.createVerticalStrut(10));
		b_BasicInfo.add(b_BasicInfo_Address);
		b_BasicInfo.add(Box.createVerticalStrut(300));

		b_ChangePassword = Box.createVerticalBox();// b_ChangePassword lÃ  quáº£n lÃ½ chung cá»§a Ä‘á»•i máº­t kháº©u

		Box b_ChangePassword_mkc = Box.createHorizontalBox();
		b_ChangePassword_mkc.add(Box.createHorizontalStrut(20));
		b_ChangePassword_mkc.add(lblOldPass = new JLabel("Nhập lại mật khẩu"));
		lblOldPass.setFont(new Font("Times new roman", Font.PLAIN, 16));
		b_ChangePassword_mkc.add(Box.createHorizontalStrut(50));
		b_ChangePassword_mkc.add(txtOldPass = new JPasswordField());
		b_ChangePassword_mkc.add(Box.createHorizontalStrut(20));

		Box b_ChangePassword_mkm = Box.createHorizontalBox();
		b_ChangePassword_mkm.add(Box.createHorizontalStrut(20));
		b_ChangePassword_mkm.add(lblNewPass = new JLabel("Nhập mật khẩu mới"));
		lblNewPass.setFont(new Font("Times new roman", Font.PLAIN, 16));
		b_ChangePassword_mkm.add(Box.createHorizontalStrut(60));
		b_ChangePassword_mkm.add(txtNewPass = new JPasswordField());
		b_ChangePassword_mkm.add(Box.createHorizontalStrut(20));

		Box b_ChangePassword_nlmkm = Box.createHorizontalBox();
		b_ChangePassword_nlmkm.add(Box.createHorizontalStrut(20));
		b_ChangePassword_nlmkm.add(lblConfirmPass = new JLabel("Nhập lại mật khẩu mới"));
		lblConfirmPass.setFont(new Font("Times new roman", Font.PLAIN, 16));
		b_ChangePassword_nlmkm.add(Box.createHorizontalStrut(40));
		b_ChangePassword_nlmkm.add(txtConfirmPass = new JPasswordField());
		b_ChangePassword_nlmkm.add(Box.createHorizontalStrut(20));

		Box b_ChangePassword_button = Box.createHorizontalBox();
		b_ChangePassword_button.add(Box.createHorizontalStrut(20));
		b_ChangePassword_button.add(btnChangePass = new JButton("Đổi mật khẩu",
				new ImageIcon(("../ima/if_Access_field_pin_input_password_2629858 .png"))));
		btnChangePass.setSize(new Dimension(50, 20));
		b_ChangePassword_button.add(Box.createHorizontalStrut(50));
		b_ChangePassword_button.add(btnCancel = new JButton("Hủy", new ImageIcon(("../ima/if_Delete_1493279.png"))));
		btnCancel.setMaximumSize(new Dimension(150, 60));
		b_ChangePassword_button.add(Box.createHorizontalStrut(20));
		b_ChangePassword.add(Box.createVerticalStrut(10));
		b_ChangePassword.add(b_ChangePassword_mkc);
		b_ChangePassword.add(Box.createVerticalStrut(10));
		b_ChangePassword.add(b_ChangePassword_mkm);
		b_ChangePassword.add(Box.createVerticalStrut(10));
		b_ChangePassword.add(b_ChangePassword_nlmkm);
		b_ChangePassword.add(Box.createVerticalStrut(10));
		b_ChangePassword.add(b_ChangePassword_button);
		b_ChangePassword.add(Box.createVerticalStrut(300));

		tbpqlcn.addTab("Xem Thông tin", b_BasicInfo);
		tbpqlcn.addTab("Thay đổi mật khẩu", b_ChangePassword);
		tbpqlcn.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		/////////////////////////////////
		/**
		 * CÃ¡i nÃ y lÃ  quáº£n lÃ½ cÃ¡c tab
		 */
		/////////////////////////////////
		tbp = new JTabbedPane();
		tbp.addTab("Thông tin cá nhân", tbpqlcn);
		tbp.addTab("Quản lý bán hàng", bqlbn);
		tbp.setFont(new Font("Times new Roman", Font.PLAIN, 20));
		tbp.setTabPlacement(JTabbedPane.LEFT);

		btnChangePass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ChangePass();
				} catch (RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new FrmMain();
				dispose();
			}
		});
		hienthithongtin();
		bt.add(tbp);
		add(bt);
		setLocationRelativeTo(null);
		setVisible(true);
		LoadAllGoodsToComboBox();

		LoadAllGoods();
		
		txtPrice.setText(listAllGoods.get(0).getPrice().toString());
		txtProductId.setText(listAllGoods.get(0).getId());
		txtQuantity.setText(listAllGoods.get(0).getQuantity().toString());

		btnSearch.addActionListener(this);
		btnAdd.addActionListener(this);
		btnRemove.addActionListener(this);
		btnPrintInvoice.addActionListener(this);

		txtPrice.setEnabled(false);
		txtProductId.setEnabled(false);

		cbbProductName.addActionListener(this);
		tableGood.addMouseListener(this);
	}

	public void LoadAllGoods() throws AccessException, RemoteException, NotBoundException {

		ArrayList<Good> listGoods = (ArrayList<Good>) goodService.findAll();

		for (Good good : listGoods) {
			listAllGoods.add(good);
		}
	}

	public void LoadAllGoodsToComboBox() throws AccessException, RemoteException, NotBoundException {
		ArrayList<Good> listGoods = (ArrayList<Good>) goodService.findAll();

		System.out.println("SUCCESS");

		for (Good good : listGoods) {
			String getName = good.getName();
			modelcbbProductName.addElement(getName);
		}
	}

	public void LoadProductByKey(String key) throws AccessException, RemoteException, NotBoundException {
		ArrayList<Good> listGoods = (ArrayList<Good>) goodService.findByProductKey(key);
		if (listGoods.size() > 0) {
			modelcbbProductName.removeAllElements();

			for (Good good : listGoods) {
				String getName = good.getName();
				modelcbbProductName.addElement(getName);
			}
		} else {
			JOptionPane.showConfirmDialog(this, "440");
		}

	}

	public void LoadProductByName(String name) throws AccessException, RemoteException, NotBoundException {
		// Good g = callService.getGoodDAO().findByProductName(name);

		for (Good good : listAllGoods) {
			if (good.getName().equals(name)) {
				txtProductId.setText(good.getId());
				txtQuantity.setText(good.getQuantity().toString());
				txtPrice.setText(good.getPrice().toString());
			}
		}
	}

	public boolean AddGood() {
		// kiểm trốngp
		// kiểm <= 0
		// kiểm tra > lớn hơn sl
		// kiểm tra trùng

		if (!txtQuantity.getText().equals("")) {
			if (Integer.parseInt(txtQuantity.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "Số lượng sản phẩm phải lớn hơn 0");
				txtQuantity.requestFocus();
			} else {
				String id = txtProductId.getText();
				String name = cbbProductName.getSelectedItem().toString();
				double price = Double.parseDouble(txtPrice.getText());
				int quantity = Integer.parseInt(txtQuantity.getText());
				double total = price * quantity;

				Good g = new Good();
				g.setId(id);
				g.setName(name);
				g.setQuantity(quantity);
				g.setPrice(price);

				boolean flag = false; // kiểm tra số lượng okay

				for (Good good : listAllGoods) {
					if (good.getId().equals(g.getId())) {

						if (g.getQuantity() > good.getQuantity()) {
							JOptionPane.showMessageDialog(this, "Quá số lượng");
							txtQuantity.selectAll();
							txtQuantity.requestFocus();
						} else {

							int temp = good.getQuantity() - g.getQuantity();
							good.setQuantity(temp);

							flag = true;
							break;
						}

					}

				}

				if (flag) { // nếu sl ok
					// kiểm tra trùng id
					boolean flagId = false;
					int sumQty = 0;

					for (Good good : listGoodsBought) {
						if (good.getId().equals(g.getId())) {
							sumQty = good.getQuantity() + g.getQuantity();
							good.setQuantity(good.getQuantity() + g.getQuantity());
							flagId = true;
						}
					}

					if (flagId) { // nếu trùng id
						for (int i = 0; i < modelGood.getRowCount(); i++) {
							if (modelGood.getValueAt(i, 0).toString().equals(g.getId())) {
								modelGood.setValueAt(sumQty, i, 2);
								RemoveTextFields();
								break;
							}
						}

					} else {
						listGoodsBought.add(g);
						String rowData[] = { id, name, Integer.toString(g.getQuantity()), Double.toString(price),
								Double.toString(total) };
						sumInvoice += total;

						// lblSumInvoice.setText(Double.toString(sumInvoice));

						modelGood.addRow(rowData);
						RemoveTextFields();
						return true;
					}

				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Số lượng không được để trống");
			txtQuantity.requestFocus();
		}
		return false;
	}

	public void RemoveTextFields() {
		txtSearch.setText("");
		modelcbbProductName.setSelectedItem("");
		txtProductId.setText("");
		txtQuantity.setText("");
		txtPrice.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj.equals(btnAdd)) {
			if (AddGood()) {
				double sum = 0;
				if (listGoodsBought.size() > 0) {
					for (Good good : listGoodsBought) {
						sum += good.getQuantity() * good.getPrice();
					}
				}
				String outString = String.format("%.2f", sum);
				lblSumInvoice.setText(outString);
			}

		} else if (obj.equals(btnRemove)) {
			int row = tableGood.getSelectedRow();
			if (row >= 0) {

				if (JOptionPane.showConfirmDialog(null, "Bạn muốn muốn xóa sản phẩm này không?", "Cảnh báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					RemoveGoodActions();
					modelGood.removeRow(row);
					try {
						LoadAllGoodsToComboBox();
					} catch (RemoteException | NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					RemoveTextFields();
					JOptionPane.showMessageDialog(this, "Xoá sản phẩm thành công");
				} else {
					// no option
				}

			} else {
				JOptionPane.showMessageDialog(this, "Chọn sản phẩm để xóa");
				tableGood.requestFocus();
			}

		} else if (obj.equals(btnPrintInvoice)) {

			Invoice in = null;
			try {
				in = inVoiceService.getLastInvoiceByDate();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (!in.equals("")) {
				JOptionPane.showMessageDialog(this, "In hóa đơn thành công");
				try {
					showReceipt(in);
					listGoodsBought.clear();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(this, "In hóa đơn thất bại do không sản phẩm nào");
			}

		} else if (obj.equals(btnSearch)) {
			try {
				SearchActions();
			} catch (RemoteException | NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (obj.equals(cbbProductName)) {
			if (cbbProductName.getItemCount() > 0) {
				String name = cbbProductName.getSelectedItem().toString();

				try {
					LoadProductByName(name);
				} catch (RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tableGood.getSelectedRow();
		if (row >= 0) {

			txtProductId.setText(tableGood.getValueAt(row, 0).toString());

			modelcbbProductName.removeAllElements();
			modelcbbProductName.setSelectedItem(tableGood.getValueAt(row, 1).toString());
			// .setText(tableGood.getValueAt(row, 1).toString());
			txtQuantity.setText(tableGood.getValueAt(row, 2).toString());
			txtPrice.setText(tableGood.getValueAt(row, 3).toString());

			/*
			 * if (table.getValueAt(row, 3).equals("NS")) { comboBox.setSelectedIndex(1); }
			 * else { comboBox.setSelectedIndex(0); }
			 */
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void hienthithongtin() throws AccessException, RemoteException, NotBoundException {
		System.out.println(account.getId());
		System.out.println("user :" + account.getUsername());
		if (callService == null)
			System.out.println("Không thể đăng nhập");
		else {
			Employee em = callService.getEmployeeDAO().getEmp(account.getId());
			txtId.setText(em.getId());
			txtFullName.setText(em.getFullName());
			txtDateOfBirth.setText(em.getBirthdate().toString());
			txtAddress.setText(em.getAddress());
		}

	}

	private void XoaTrangDoiMatKhau() {
		txtOldPass.setText("");
		txtConfirmPass.setText("");
		txtNewPass.setText("");
	}

	public boolean ChangePass() throws AccessException, RemoteException, NotBoundException {
		String mkc = "", mkm = "", nlmkm = "";
		char a[], b[], c[];
		a = ((JPasswordField) txtOldPass).getPassword();
		b = ((JPasswordField) txtConfirmPass).getPassword();
		c = ((JPasswordField) txtNewPass).getPassword();
		for (int i = 0; i < a.length; i++) {
			mkc = mkc + a[i];
		}
		for (int i = 0; i < b.length; i++) {
			mkm = mkm + b[i];
		}
		for (int i = 0; i < c.length; i++) {
			nlmkm = nlmkm + c[i];
		}
		Account ac = new Account();
		ac.setId(account.getId());
		ac.setType(account.getType());
		ac.setStartingDate(account.getStartingDate());
		ac.setStatus(account.getStatus());
		ac.setUsername(account.getUsername());
		ac.setPassword(mkm);

		if (!callService.getAccountDAO().checkPassOld(account.getPassword(), mkc)) {
			JOptionPane.showMessageDialog(new JFrame(), "Mật khẩu cũ không khớp");
			XoaTrangDoiMatKhau();
			return false;
		} else if (!mkm.equals(nlmkm)) {
			JOptionPane.showMessageDialog(new JFrame(), "Nhập lại mật khẩu mới không khớp");
			XoaTrangDoiMatKhau();
			return false;
		}
		callService.getAccountDAO().update(ac);
		JOptionPane.showMessageDialog(new JFrame(), "Đổi mật khẩu thành công");
		return true;
	}

	public Invoice PrintInvoiceActions() throws AccessException, RemoteException, NotBoundException {


		if (listGoodsBought.size() > 0) {
			/*
			 * Employee em = new Employee(); Account ac = new Account();
			 * ac.setUsername("Luan"); em.setAccount(ac);
			 */


			Employee getEm = employeeService.getEmp(account.getId());

			/*
			 * Customer customer = new Customer(); customer.setFullName("Khach hang 1");
			 * customer.setAddress("TP HCM");
			 */
			Invoice inVoice = new Invoice();

			inVoice.setInvoiceDate(new Date());
			// inVoice.setCustomer(customer);
			inVoice.setEmployee(getEm);

			InvoiceDetails inVoiceDetails = new InvoiceDetails();

			inVoiceDetails.setGoods(listGoodsBought);
			inVoiceDetails.setInvoice(inVoice);
			inVoiceDetails.setTotal(sumInvoice);
			invoideDetailsService.save(inVoiceDetails);

			System.out.println("id: " + inVoiceDetails.getId());

			modelGood.setRowCount(0);
			updateGoods();
			lblSumInvoice.setText("");
			RemoveTextFields();
			return inVoice;
		}

		return null;
	}

	public void updateGoods() throws AccessException, RemoteException, NotBoundException {

		for (int i = 0; i < listGoodsBought.size(); i++) {
			for (int j = 0; j < listAllGoods.size(); j++) {
				if (listGoodsBought.get(i).getId().equals(listAllGoods.get(j).getId())) {
					goodService.update(listAllGoods.get(j));
				}
			}
		}
	}

	public void SearchActions() throws AccessException, RemoteException, NotBoundException {
		if (txtSearch.getText() != null) {
			String key = txtSearch.getText();
			LoadProductByKey(key);
		}
	}

	public boolean RemoveGoodActions() {
		// 1 tìm obj xóa trong list mua hàng
		// 2 lấy obj tìm trong list sản phẩm
		// 3 + thêm vào

		int row = tableGood.getSelectedRow();
		Good goodSelected = null;

		if (row >= 0) {
			for (Good good : listGoodsBought) {
				if (good.getId().equals(tableGood.getValueAt(row, 0))) {
					goodSelected = good;
					listGoodsBought.remove(good);
				}
				break;
			}
			for (Good good : listAllGoods) {
				if (good.getId().equals(goodSelected.getId())) {
					good.setQuantity(good.getQuantity() + goodSelected.getQuantity());
				}
			}
		}
		return false;

	}

	private void showReceipt(Invoice invoice) throws RemoteException {
		JFrame frame = new JFrame("Hóa đơn");
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Font font = new Font("monospaced", Font.PLAIN, 18);

		InvoiceDetails inD = invoideDetailsService.findByInvoiceId(invoice.getId());

		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.WHITE);

		JLabel lblTitile, lblTitle2, lblTitile3;

		pNorth.setLayout(new BoxLayout(pNorth, BoxLayout.Y_AXIS));

		pNorth.add(lblTitile = new JLabel("SIÊU THỊ MINI", JLabel.CENTER));
		lblTitile.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		pNorth.add(Box.createVerticalStrut(5));
		pNorth.add(lblTitle2 = new JLabel("12-Nguyễn Văn Bảo-Phường 4-Gò Vấp-TP.HCM", JLabel.CENTER));
		lblTitle2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		pNorth.add(Box.createVerticalStrut(5));
		pNorth.add(lblTitile3 = new JLabel("***********************", JLabel.CENTER));
		lblTitile3.setAlignmentX(JLabel.CENTER_ALIGNMENT);

		pNorth.add(Box.createVerticalStrut(50));

		frame.add(pNorth, BorderLayout.NORTH);

		//

		JPanel pCenter = new JPanel();
		pCenter.setBackground(Color.WHITE);
		pCenter.setLayout(new FlowLayout(FlowLayout.LEFT));

		Box b = Box.createVerticalBox();

		Box b1, b2, b3, b4, b5, b6, b7, b8, b9;

		JLabel lblMa, lblTenKH, lblTenSP, lblTenNV, lblSoLuong, lblTongTien, lblDiaChi, lblNgayGui, lblSum;
		JLabel lblMa2, lblTenKH2, lblTenSP2, lblTenNV2, lblSoLuong2, lblTongTien2, lblDiaChi2, lblNgayGui2;

		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMa = new JLabel("Mã đặt hàng:"));
		b1.add(Box.createHorizontalStrut(50));
		b1.add(lblMa2 = new JLabel(invoice.getId()));

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblTenKH = new JLabel("Tên khách hàng:"));
		b2.add(Box.createHorizontalStrut(50));
		b2.add(lblTenKH2 = new JLabel("Khach vang lai"));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblTenNV = new JLabel("Tên nhân viên:"));
		b4.add(Box.createHorizontalStrut(50));
		b4.add(lblTenNV2 = new JLabel("nv1"));

		String[] headers = "Tên sản phẩm; Số lượng; Giá mỗi sản phẩm; Thành tiền".split(";");
		modelGoodsBought = new DefaultTableModel(headers, 0);
		b.add(new JScrollPane(tableGoodsBought = new JTable(modelGoodsBought)));

		LoadAllBoughtToTable(inD);

		pCenter.add(b);

		frame.add(pCenter, BorderLayout.CENTER);

		//

		JPanel pSouth = new JPanel();
		pSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
		pSouth.setBackground(Color.WHITE);

		JLabel lblBottom1, lblBottom2;

		Box bot = Box.createVerticalBox();

		bot.add(b9 = Box.createHorizontalBox());
		bot.add(Box.createVerticalStrut(10));
		b9.add(lblNgayGui = new JLabel("Tổng tiền:"));
		b9.add(Box.createHorizontalStrut(50));
		b9.add(lblSum = new JLabel());
		lblSum.setText(lblSumInvoice.getText());

		bot.add(b8 = Box.createHorizontalBox());
		bot.add(Box.createVerticalStrut(10));
		b8.add(lblNgayGui = new JLabel("Ngày:"));
		b8.add(Box.createHorizontalStrut(50));

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		b8.add(lblNgayGui2 = new JLabel(sdf.format(invoice.getInvoiceDate())));

		bot.add(new JLabel("--------------------------------------"));
		bot.add(Box.createVerticalStrut(10));
		bot.add(lblBottom1 = new JLabel("Cảm ơn quý khách"));
		bot.add(Box.createVerticalStrut(10));
		bot.add(lblBottom2 = new JLabel("Xin hẹn gặp lại"));

		pSouth.add(bot);

		frame.add(pSouth, BorderLayout.SOUTH);

		lblTitile.setFont(font);
		lblTitle2.setFont(font);
		lblTitile3.setFont(font);
		lblMa.setFont(font);
		lblMa2.setFont(font);
		lblTenKH.setFont(font);
		lblTenKH2.setFont(font);
	}

	public void LoadAllBoughtToTable(InvoiceDetails inD) {
		List<Good> listBought = inD.getGoods();

		for (Good good : listGoodsBought) {
			String name = good.getName();
			int quantity = good.getQuantity();
			double price = good.getPrice();
			double sum = quantity * price;
			String rowData[] = { name, Integer.toString(quantity), Double.toString(price), Double.toString(sum) };

			modelGoodsBought.addRow(rowData);
		}
	}

}