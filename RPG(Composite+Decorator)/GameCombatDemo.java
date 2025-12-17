import java.util.ArrayList;
import java.util.List;
interface Skill {
    int getDamage();
    String getDescription();
}

class BasicSkill implements Skill{
    private String name;
    private int damage;
    public BasicSkill(String name,int damage){
        this.damage = damage;
        this.name = name;
    }
    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public String getDescription() {
        return this.name;
    }
}
class ComboSkill implements Skill{
    private String comboName;
    private List<Skill> skills = new ArrayList<>();
    public ComboSkill(String name) {
        this.comboName = name;
    }
    public void addSkill(Skill s){
        skills.add(s);
    }

    @Override
    public int getDamage() {
        int total = 0;
        for (Skill s: skills){
            total += s.getDamage();
        }
        return total;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder(comboName + " [");
        for (int i = 0; i < skills.size(); i++) {
            sb.append(skills.get(i).getDescription());
            if (i < skills.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
abstract class SkillDecorator implements Skill{
    private Skill wrappedSkill;
    public SkillDecorator(Skill skill){
        this.wrappedSkill = skill;
    }
    @Override
    public int getDamage() {
        return wrappedSkill.getDamage();
    }

    @Override
    public String getDescription() {
        return wrappedSkill.getDescription();
    }
}
class FireBuff extends SkillDecorator{
    public FireBuff(Skill skill){
        super(skill);
    }
    @Override
    public int getDamage() {
        return super.getDamage() + 5; 
    }
    @Override
    public String getDescription() {
        return super.getDescription() + " (Cháy bỏng)";
    }
}
class IceBuff extends SkillDecorator {
    public IceBuff(Skill skill) {
        super(skill);
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 3; // Thêm 3 dmg
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (Làm chậm)";
    }
}
public class GameCombatDemo {
    public static void main(String[] args) {
        // 1. Tạo các chiêu lẻ (Leaf)
        Skill dam = new BasicSkill("Cú Đấm", 10);
        Skill da = new BasicSkill("Cú Đá", 15);

        // 2. Tạo một Combo (Composite)
        ComboSkill lienHoan = new ComboSkill("Liên Hoàn");
        lienHoan.addSkill(dam);
        lienHoan.addSkill(da);

        System.out.println("--- Combo gốc ---");
        System.out.println("Skill: " + lienHoan.getDescription());
        System.out.println("Damage: " + lienHoan.getDamage()); 
        // 10 + 15 = 25
        
        // 3. Cường hóa Combo bằng Lửa (Decorator bọc Composite)
        // Đây là điểm ăn tiền: Decorator không quan tâm bên trong là 1 chiêu hay 1 combo
        Skill comboLua = new FireBuff(lienHoan);

        // 4. Cường hóa tiếp bằng Băng (Decorator bọc Decorator)
        Skill comboLuaBang = new IceBuff(comboLua);

        System.out.println("\n--- Combo sau khi Buff Lửa & Băng ---");
        System.out.println("Skill: " + comboLuaBang.getDescription());
        System.out.println("Damage: " + comboLuaBang.getDamage());
        // 25 (Gốc) + 5 (Lửa) + 3 (Băng) = 33
    }
}
