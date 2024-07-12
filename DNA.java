import java.util.LinkedList;

/**
 * @author prusuit of light
 * @content DNA的压缩存储和恢复算法
 * @date 2024/7/12
 */

public class contains {
//    static String[] TABOOSTRING = {"AAA", "ACT", "CATA"};
    static String[] TABOOSTRING = {"AC", "TA"};  // 禁忌串
    static StringBuilder TEXT = new StringBuilder("ACTAACATAAAGG");  // DNA串

    /**
     * 自定义一个字典数据结构，用于存储在原DNA串中剔除的禁忌串的位置和碱基
     */
    static class dict{
        int index;
        String str;

        public dict(int a, String b)
        {
            index = a;
            str = b;
        }
    }
    static LinkedList<dict> Store_memory = new LinkedList<>(); // 字典链表

    public static void main(String[] args) {
        StringBuilder txt = remove();
        System.out.println(txt);
//        print_dict_mem();
        txt = restore(txt);
        System.out.println(txt);
    }
    /**
     * 剔除函数
     * @return 返回剔除了禁忌串的DNA序列
     */
    public static StringBuilder remove()
    {
        StringBuilder txt = new StringBuilder();
        String temp = "";
        for(int i=0; i<TEXT.length(); i++)  //将原NDA序列一个一个加入到txt中
        {
            txt.insert(txt.length(), String.valueOf(TEXT.charAt(i)));
            temp = txt.toString();          //将txt转成String类型
            for (String taboo: TABOOSTRING
            ) {
                if(temp.contains(taboo))    //String类型中contains函数 直接在temp中检查是否存在taboo
                {
//                    System.out.println(taboo);
                    int index = temp.indexOf(taboo);   //获取temp序列中存在禁忌串的起始位置
                    dict d = new dict(index, taboo);   //将起始位置和禁忌串以字典的数据结构存储
                    Store_memory.addLast(d);
                    //将temp序列中的禁忌串剔除
                    temp = temp.substring(0, index) + temp.substring(index + taboo.length());
                    txt= new StringBuilder(temp);      //将String类型转换成StringBuilder类型
                }
            }
        }
//        System.out.println(temp);
        return new StringBuilder(temp);
    }

    /**
     * 打印字典的内容 用于检验
     */
    public static void print_dict_mem()
    {
        for (dict d: Store_memory
             ) {
            System.out.println(d.index);
            System.out.println(d.str);
        }
    }

    /**
     * 恢复函数
     * @param txt 已剔除禁忌串的DNA序列
     * @return txt 返回恢复后的DNA序列
     */
    public static StringBuilder restore(StringBuilder txt)
    {
        int count = Store_memory.size();  //恢复的次数
        int len = count-1;
        String temp = txt.toString();
        for(int i=0; i<count; i++)
        {
            txt.insert(Store_memory.get(len).index, Store_memory.get(len).str);  //逆序恢复
            len--;
        }
        return txt;
    }
}