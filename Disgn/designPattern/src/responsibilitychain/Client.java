package responsibilitychain;

public class Client {
    public static void main(String[] args) {
        //创建一个请求
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 3000, 1);
        //创建一个相关的审批人
        DepartmentApprover departmentApprover = new DepartmentApprover("张主任");
        CollegeApprover collegeApprover = new CollegeApprover("李院长");
        ViceSchoolMasterApprover viceSchoolMasterApprover = new ViceSchoolMasterApprover("王副校长-");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("校长");
        //需要将各个审批级别的下一个设置好（处理人构成一个环形:一定要找到一个人处理 ，任何人调用processRequest ，是环形，优于链状）
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setApprover(schoolMasterApprover);
        schoolMasterApprover.setApprover(departmentApprover);
        schoolMasterApprover.processRequest(purchaseRequest);

    }
}
