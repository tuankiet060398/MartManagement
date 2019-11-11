package group.jpa.ogm.app.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

import group.jpa.ogm.app.controller.client.ClientController;
import group.jpa.ogm.app.entities.Category;
import group.jpa.ogm.app.entities.Good;
import group.jpa.ogm.app.repository.category.CategoryDAO;
import group.jpa.ogm.app.repository.goods.GoodDAO;

public class TreeGoods extends JPanel {

	public JTree tree;
	public DefaultTreeModel model;
	public DefaultMutableTreeNode rootNode;
	private GoodDAO goodService;
	private CategoryDAO categoryService;

	static ClientController callService;

	public TreeGoods() throws RemoteException, NotBoundException {
		super(new GridLayout(1, 0));

		rootNode = new DefaultMutableTreeNode("Kho");
		model = new DefaultTreeModel(rootNode);

		callService = new ClientController("192.168.31.109", 9999);

		goodService = callService.getGoodDAO();
		categoryService = callService.getCategoryDAO();

		tree = new JTree(model);

		System.out.println("reload");

		LoadGoodsToTree();

		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane, BorderLayout.NORTH);
	}

	/** Add child to the currently selected node. */
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		System.out.println("parentPath: " + parentPath);

		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		return addObject(parent, child, false);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = rootNode;
		}

		// It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
		model.insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	public void LoadGoodsToTree() throws AccessException, RemoteException, NotBoundException {
		List<Category> listCategories = categoryService.findAll();

		System.out.println("size category: " + listCategories.size());

		if (listCategories.size() > 0) {
			for (Category category : listCategories) {
				DefaultMutableTreeNode cate = new DefaultMutableTreeNode(category.getName());
				rootNode.add(cate);

				List<Good> listGoodsByCategory = goodService.findGoodsByCategoryId(category.getId());
				// System.out.println("size goods by category: " + listGoodsByCategory.size());

				if (listGoodsByCategory.size() > 0) {
					for (Good g : listGoodsByCategory) {
						cate.add(new DefaultMutableTreeNode(g.getName()));
					}
				}

			}
		} else {
			System.out.println("NO");
		}
	}

}
