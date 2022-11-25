package com.benjamin.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GrapeCity {
    // 编程语言：不限
    // 题目描述：有句话是这么说的：“文不如表，表不如图”。形象地描述了图表在传达信息时，给接收者带来的截然不同的效率和体验。因此，在计算机计算能力、数据规模和决策需求都不断提升的当下，数据可视化的应用也越来越普遍。
    // 数据可视化的范围很广，涉及到数据的获取、加工、建模、图形学，人机交互等很多概念和领域，想更快上手，获得更好的体验，
    // 使用DragonFly BI这样的专业工具和服务是更明智的选择。
    // 今天，我们通过一个简化的命题，来亲手实现简单的数据可视化。编写一个程序，对于给定的一组数据和要求，输出一个以字符组成的柱状图。

    // 输入
    // 第一行，一个整数
    // N（1<=n<=20），表示这组数据的条目数。第二行，两个字符串，用于表示数据展示在柱状图上的排序方式。第一个字符串是“Name”或者“Value”，表示排序的依据是数据条目的名称亦或数值；第二个字符串是“ASC”或者“DESC”，表示升序或降序。
    // 随后的 N 行，
    // 每行包含一个字符串 S
    // 和一个数字 V，以空格分隔，表示一条数据。S 即数据条目的名称，仅包含小写字母，
    // V 即对应的数值，是一个整数，(0<=V<=1,000,000)</P>

    // 输出
    // 图表外框转角符号：
    // “┌”（\u250c）“┐”（\u2510）“└”（\u2514）“┘”（\u2518）图表中的横、竖线：
    // “─”（\u2500）“│”（\u2502）图表中的各种交叉线：
    // “├”（\u251c）“┤”（\u2524）“┬”（\u252c）“┴”（\u2534）“┼”（\u253c）用来拼柱子的字符：
    // “█”（\u2588）图表中的空格：
    // “”（）图表中名称区域的宽度，由这组数据中名称的最大长度决定，所有名称向右对齐，图表中柱的最大长度为
    // 20，每个柱的长度由该柱对应数据和这组数据中最大值（此值一定大于 0）的比值与 20 相乘获得，不足一格的部分舍去。

    // 输入示例
    // 3
    // Value DESC
    // apple 5
    // pen 3
    // pineapple 10

    // 输出示例
    // ┌─────────┬────────────────────┐
    // │pineapple│████████████████████│
    // ├─────────┼────────────────────┤
    // │····apple│██████████··········│
    // ├─────────┼────────────────────┤
    // │······pen│██████··············|
    // └─────────┴────────────────────┘

    private final class ComparatorImplementation implements Comparator<Data> {
        boolean isGroupByName;
        boolean isGroupDESC;

        public ComparatorImplementation(String groupBy, String group) {
            this.isGroupByName = groupBy.equals("Name");
            this.isGroupDESC = group.equals("DESC");
        }

        @Override
        public int compare(Data o1, Data o2) {
            if (isGroupByName) {
                if (o1.name.compareTo(o2.name) > 0) {
                    return isGroupDESC ? 1 : -1;
                }
                return !isGroupDESC ? 1 : -1;
            } else {
                if (o1.value > o2.value) {
                    return isGroupDESC ? 1 : -1;
                }
                return !isGroupDESC ? 1 : -1;
            }
        }

    }

    class Data {
        private String name;
        private int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Data(String name, int value) {
            this.name = name;
            this.value = value;
        }

    }

    public List<Data> datas;
    public int bigestNameLength;
    public int bigestValue;

    public String nameLine;
    public String valueLine;

    public void initFromSystemIO() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        ComparatorImplementation c = new ComparatorImplementation(in.next(), in.next());
        in.nextLine();
        datas = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String name = in.next();
            int score = in.nextInt();
            datas.add(new Data(name, score));
            in.nextLine();
            bigestNameLength = Math.max(name.length(), bigestNameLength);
            bigestValue = Math.max(score, bigestValue);
        }
        in.close();

        StringBuilder nameLineBuilder = new StringBuilder();
        for (int i = 0; i < bigestNameLength; i++) {
            nameLineBuilder.append('─');
        }
        nameLine = nameLineBuilder.toString();
        StringBuilder vauleLineBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            vauleLineBuilder.append('─');
        }
        valueLine = vauleLineBuilder.toString();

        Collections.sort(datas, c);
    }

    public String output() {
        // “┌”（\u250c）“┐”（\u2510）“└”（\u2514）“┘”（\u2518）图表中的横、竖线：
        // “─”（\u2500）“│”（\u2502）图表中的各种交叉线：
        // “├”（\u251c）“┤”（\u2524）“┬”（\u252c）“┴”（\u2534）“┼”（\u253c）用来拼柱子的字符：
        // “█”（\u2588）图表中的空格：
        // “”（）图表中名称区域的宽度，由这组数据中名称的最大长度决定，所有名称向右对齐，图表中柱的最大长度为
        // 20，每个柱的长度由该柱对应数据和这组数据中最大值（此值一定大于 0）的比值与 20 相乘获得，不足一格的部分舍去。
        // 输入示例
        // 3
        // Value DESC
        // apple 5
        // pen 3
        // pineapple 10

        // 输出示例
        // ┌─────────┬────────────────────┐
        // │pineapple│████████████████████│
        // ├─────────┼────────────────────┤
        // │····apple│██████████··········│
        // ├─────────┼────────────────────┤
        // │······pen│██████··············|
        // └─────────┴────────────────────┘

        StringBuilder outputBuilder = new StringBuilder();
        appendFirstLine(outputBuilder);
        for (int i = 0; i < datas.size(); i++) {
            Data data = datas.get(i);

            appendDataLine(outputBuilder, data);

            if (i < datas.size() - 1) {
                appendBetweenDataLine(outputBuilder);
            }
        }

        appendLastLine(outputBuilder);

        return outputBuilder.toString();
    }

    private void appendDataLine(StringBuilder outputBuilder, Data data) {
        outputBuilder.append('│');
        for (int j = 0; j < bigestNameLength - data.getName().length(); j++) {
            outputBuilder.append(' ');
        }
        outputBuilder.append(data.getName()).append('│');
        for (int j = 0; j < 20; j++) {
            if (j < (20 * data.getValue() / bigestValue)) {
                outputBuilder.append('█');
            } else {
                outputBuilder.append(' ');
            }
        }
        outputBuilder.append('│').append('\n');
    }

    private void appendFirstLine(StringBuilder outputBuilder) {
        outputBuilder.append('┌').append(nameLine).append('┬').append(valueLine).append('┐').append('\n');
    }

    private void appendBetweenDataLine(StringBuilder outputBuilder) {
        outputBuilder.append('├').append(nameLine).append('┼').append(valueLine).append('┤').append('\n');
    }

    private void appendLastLine(StringBuilder outputBuilder) {
        outputBuilder.append('└').append(nameLine).append('┴').append(valueLine).append('┘').append('\n');
    }

    public static void main(String[] args) {
        System.out.println("3\nName ASC\napple 5\npen 3\npineapple 10");

        GrapeCity gc = new GrapeCity();
        gc.initFromSystemIO();
        String output = gc.output();
        System.out.println(output);
    }
}
