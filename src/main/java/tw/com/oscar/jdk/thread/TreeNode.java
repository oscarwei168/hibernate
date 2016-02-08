/**
 * TreeNode.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/8
 * <p>
 * H i s t o r y
 * 2016/2/8 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: TreeNode.java<br>
 * </p>
 * <strong>Description:</strong> A deadlock example codes <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/2/8
 * @since 2016/2/8
 */
public class TreeNode {

    TreeNode parent = null;
    List<TreeNode> children = new ArrayList();

    public synchronized void addChild(TreeNode child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
            child.setParentOnly(this);
        }
    }

    public synchronized void addChildOnly(TreeNode child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
        }
    }

    public synchronized void setParent(TreeNode parent) {
        this.parent = parent;
        parent.addChildOnly(this);
    }

    public synchronized void setParentOnly(TreeNode parent) {
        this.parent = parent;
    }
}
