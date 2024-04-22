package CommandLine;

public class Hangman extends Game {
    private boolean L_hold;
    private boolean hangManHead;
    private boolean hangManBody;
    private boolean hangManArm1;
    private boolean hangManArm2;
    private boolean hangManLeg1;
    private boolean hangManLeg2;

    public Hangman() {
        super();
        L_hold = false;
        hangManHead = false;
        hangManBody = false;
        hangManArm1 = false;
        hangManArm2 = false;
        hangManLeg1 = false;
        hangManLeg2 = false;
    }
    public void L_Hold() {
        this.L_hold = true;
        System.out.print( "[################################]\n" +
                "||                        \n" +
                "||                          \n" +
                "||                          \n" +
                "||                            \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n");
    }

    public void hangHead() {
        this.hangManHead = true;
        System.out.print( "[################################]\n" +
                "||                        |        \n" +
                "||                      #####      \n" +
                "||                     #######      \n" +
                "||                      #####       \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n");
    }

    public void hangBody() {
        this.hangManBody = true;
        System.out.print( "[################################]\n" +
                "||                        |      \n" +
                "||                      #####       \n" +
                "||                     #######      \n" +
                "||                      #####       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n");
    }

    public void hangArm1() {
        this.hangManArm1 = true;
        System.out.print("[################################]\n" +
                "||                        |      \n" +
                "||                      #####       \n" +
                "||                     #######      \n" +
                "||                      #####       \n" +
                "||                        #       \n" +
                "||                   # # ##       \n" +
                "||                 #      #       \n" +
                "||               #        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n");
    }
    public void hangArm2() {
        this.hangManArm2 = true;
        System.out.print("[################################]\n" +
                "||                        |      \n" +
                "||                      #####       \n" +
                "||                     #######      \n" +
                "||                      #####       \n" +
                "||                        #       \n" +
                "||                   # # ### # #     \n" +
                "||                 #      #      #  \n" +
                "||               #        #        #\n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n" +
                "||                                \n");
    }
    public void hangLeg1() {
        this.hangManLeg1 = true;
        System.out.print("[################################]\n" +
                "||                        |      \n" +
                "||                      #####       \n" +
                "||                     #######      \n" +
                "||                      #####       \n" +
                "||                        #       \n" +
                "||                   # # ### # #     \n" +
                "||                 #      #      #  \n" +
                "||               #        #        #\n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                     # ##       \n" +
                "||                    #            \n" +
                "||                   #             \n" +
                "||                  #             \n" +
                "||                 #               \n");
    }
    public void hangLeg2() {
        this.hangManLeg2 = true;
        System.out.print("[################################]\n" +
                "||                        |      \n" +
                "||                      #####       \n" +
                "||                     #######      \n" +
                "||                      #####       \n" +
                "||                        #       \n" +
                "||                   # # ### # #     \n" +
                "||                 #      #      #  \n" +
                "||               #        #        #\n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                        #       \n" +
                "||                     # ### #       \n" +
                "||                    #       #     \n" +
                "||                   #         #    \n" +
                "||                  #           #  \n" +
                "||                 #             #  \n");
    }
    public boolean isHangMan() {
        return this.L_hold&&this.hangManHead&&this.hangManArm1 && this.hangManBody
                &&this.hangManArm2&&this.hangManLeg1&&this.hangManLeg2;
    }
}
