package test.demo.prototype.DeepCloneable;

import java.io.Serializable;

/**
 * @author
 * @date 2021 年 12 月 02 日
 */
public class DeepCloneableTarget  implements Serializable,Cloneable {
  private  static  final  long serialVersionUID =1L;
  private  String cloneName;
  private  String cloneClass;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCloneName() {
        return cloneName;
    }

    public void setCloneName(String cloneName) {
        this.cloneName = cloneName;
    }

    public String getCloneClass() {
        return cloneClass;
    }

    public void setCloneClass(String cloneClass) {
        this.cloneClass = cloneClass;
    }

    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

   //该类都是string类型 的 ，所以直接用默认的clone方法即可
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
