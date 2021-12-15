package prototype.DeepCloneable;



/**
 * @author
 * @date 2021 年 12 月 02 日
 */
public class test {
    public static void main(String[] args) {

        DeepCloneableTarget deepCloneableTarget  =new DeepCloneableTarget("sl","cc");
        DeepProtoType deepProtoType = new DeepProtoType("duoli", deepCloneableTarget);

/*            DeepProtoType o = (DeepProtoType) deepProtoType.deepClone();
        System.out.println( o.getDeepCloneableTarget() );
        System.out.println(deepProtoType.getDeepCloneableTarget());*/


        DeepProtoType clone = null;
        try {
            clone = (DeepProtoType)deepProtoType.clone();
            System.out.println(   clone.getDeepCloneableTarget()==deepProtoType.getDeepCloneableTarget());
            DeepCloneableTarget      deepCloneableTarget2 =  (DeepCloneableTarget)deepCloneableTarget.clone();
            System.out.println(deepCloneableTarget2.getCloneName()==deepCloneableTarget.getCloneName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }



    }
}
