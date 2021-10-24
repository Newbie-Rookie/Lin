package com.model;

/**
 *  操作数类
 *  所有操作数以分数形式存储，整数分母为1
 */
public class Operand {
    // 分子
    private int molecule;
    // 分母
    private int denominator;

    public Operand(){
    }

    /**
     * 整数和分数一起处理
     * 整数分母为1，分数分母不为0
     * @param molecule
     * @param denominator
     */
    public Operand(int molecule, int denominator) {
        this.molecule = molecule;
        // 将为0的数(分子为0)，将分母统一置为1
        if(molecule == 0){
            this.denominator = 1;
        }else{
            this.denominator = denominator;
        }
        // 约分处理
        reduction();
    }

    /**
     * 获取分子
     * @return
     */
    public int getMolecule() {
        return this.molecule;
    }

    /**
     * 获取分母
     * @return
     */
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * 求两数之间的最大公因数并进行约分处理
     */
    public void reduction(){
        // 求最大公因数
        int maxCommonFactior = divisionAlgorithm(this.molecule,this.denominator);
        // 约分
        this.molecule /= maxCommonFactior;
        this.denominator /= maxCommonFactior;
    }

    /**
     * 辗转相除法求最大公因数
     * @param n1 第一个数
     * @param n2 第二个数
     * @return 返回最大公因数
     */
    private int divisionAlgorithm(int n1, int n2) {
        return (n2 == 0) ? n1 : divisionAlgorithm(n2,n1 % n2);
    }

    @Override
    public String toString() {
        if(!(this.molecule % this.denominator == 0)){
            // 假分数
            if(this.molecule > this.denominator){
                // 将假分数转化为带分数
                return (this.molecule / this.denominator) + "'" + this.molecule % this.denominator + "/" + this.denominator;
            }
            // 真分数
            return this.molecule + "/" + this.denominator;
        }
        // 整数
        return "" + this.molecule / this.denominator;
    }

    /**
     * 将字符串表示的数转换为Operand对象
     * @param oper
     * @return
     */
    public static Operand stringToOperand(String oper){
        // 若为带分数
        if(oper.contains("'")){
            // 将带分数字符串以"'"分割为两部分(整数 + 真分数)
            String[] opers1 = oper.split("'");
            int mix = Integer.parseInt(opers1[0]);
            // 将真分数字符串以"/"分割为两部分(分子 + 分母)
            String[] opers2 = opers1[1].split("/");
            // 分母
            int denominator = Integer.parseInt(opers2[1]);
            // 真正的分子
            int molecule = mix * denominator + Integer.parseInt(opers2[0]);
            // 创建操作数对象并返回
            return new Operand(molecule,denominator);
        }else if(oper.contains("/")){
            // 将真分数字符串以"/"分割为两部分(分子 + 分母)
            String[] opers = oper.split("/");
            // 分子
            int molecule = Integer.parseInt(opers[0]);
            // 分母
            int denominator = Integer.parseInt(opers[1]);
            // 创建操作数对象并返回
            return new Operand(molecule,denominator);
        }
        // 整数
        // 创建操作数对象并返回
        return new Operand(Integer.parseInt(oper),1);
    }

    /**
     * 加法
     * @param a
     * @param b
     * @return
     */
    public static Operand add(Operand a, Operand b) {
        int molecule = a.molecule * b.denominator + a.denominator * b.molecule;
        int denominator = a.denominator * b.denominator;
        return new Operand(molecule, denominator);
    }

    /**
     * 减法
     * @param a
     * @param b
     * @return
     */
    public static Operand sub(Operand a, Operand b) {
        int molecule = a.molecule * b.denominator - a.denominator * b.molecule;
        int denominator = a.denominator * b.denominator;
        // 减法结果为负时，返回null，提示调用方丢弃表达式
        if(molecule * denominator < 0){
            return null;
        }
        return new Operand(molecule,denominator);
    }

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    public static Operand mul(Operand a, Operand b) {
        int molecule = a.molecule * b.molecule;
        int denominator = a.denominator * b.denominator;
        return new Operand(molecule, denominator);
    }

    /**
     * 除法
     * @param a
     * @param b
     * @return
     */
    public static Operand div(Operand a, Operand b) {
        // 除数为0时，返回null，提示调用方丢弃表达式
        if(b.molecule == 0){
            return null;
        }
        int molecule = a.molecule * b.denominator;
        int denominator = a.denominator * b.molecule;
        return new Operand(molecule, denominator);
    }
}