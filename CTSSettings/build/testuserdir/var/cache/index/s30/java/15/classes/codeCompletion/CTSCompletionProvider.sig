����   3 �
 8 �
 8 �
 9 �
 W � �
 W �
 � � �
 � �
 W �
 8 � �
 � �
 � �
 Y �
 Y �
 � � A
 X �
 � �
 � �
 � �
 � � �
 � � �
 � � � �
  �
  �	 � � � �
 � � � � �  �
 � �  � o � o �
 X � �
 - � �
 - �
 - � � �  �
 - �
 & �
 & � � � � InnerClasses <init> ()V Code LineNumberTable LocalVariableTable this &LcodeCompletion/CTSCompletionProvider; 
createTask W(ILjavax/swing/text/JTextComponent;)Lorg/netbeans/spi/editor/completion/CompletionTask; ex Ljava/io/FileNotFoundException; methodCallExpr /Lcom/github/javaparser/ast/expr/MethodCallExpr; line Ljava/lang/String; 
methodName methodScope comment 	className 'Ljavax/swing/text/BadLocationException; 	queryType I jtc !Ljavax/swing/text/JTextComponent; doc !Ljavax/swing/text/StyledDocument; StackMapTable � � � #org.netbeans.SourceLevelAnnotations Ljava/lang/Override; getLine 0(Ljavax/swing/text/Document;I)Ljava/lang/String; Ljavax/swing/text/Document; caretOffset bDoc lineStartOffset 
Exceptions indexOfWhite ([C)I c C [C i getRowFirstNonWhite %(Ljavax/swing/text/StyledDocument;I)I offSet lineElement Ljavax/swing/text/Element; start � getAutoQueryTypes 6(Ljavax/swing/text/JTextComponent;Ljava/lang/String;)I string 
access$000 K(LcodeCompletion/CTSCompletionProvider;Ljavax/swing/text/StyledDocument;I)I x0 x1 x2 
access$100 +(LcodeCompletion/CTSCompletionProvider;[C)I 
SourceFile CTSCompletionProvider.java 5Lorg/netbeans/api/editor/mimelookup/MimeRegistration; mimeType text/x-java service 7Lorg/netbeans/spi/editor/completion/CompletionProvider; c d i j < = � � javax/swing/text/StyledDocument � � � � � java/io/FileNotFoundException � � � � � \ ]   � � � � � � � � � � � � � � � � � � � � � super � � � heeeeey � � >org/netbeans/spi/editor/completion/support/AsyncCompletionTask &codeCompletion/CTSCompletionProvider$1 < � < � � � � codeCompletion/ColorCodes &[36;40;1mDOCUMENTATION_QUERY_TYPE[0m � � � '[36;40;1mCOMPLETION_ALL_QUERY_TYPE[0m 1[36;40;1mIS THERE ANY OTHER TYPE OF QUERY ??[0m %javax/swing/text/BadLocationException � � � � � � � � � � � � � java/lang/StringBuilder calling getText( � � � � ,  ) on doc of length:  � � < � � � $codeCompletion/CTSCompletionProvider java/lang/Object 5org/netbeans/spi/editor/completion/CompletionProvider javax/swing/text/JTextComponent java/lang/String -com/github/javaparser/ast/expr/MethodCallExpr javax/swing/text/Element getDocument ()Ljavax/swing/text/Document; getText ()Ljava/lang/String; codeCompletion/MethodOperations setCurrentFile (Ljava/lang/String;)V org/openide/util/Exceptions printStackTrace (Ljava/lang/Throwable;)V getCaretPosition ()I isItAMethodCall (Ljava/lang/String;)Z getMethodExpr C(Ljava/lang/String;)Lcom/github/javaparser/ast/expr/MethodCallExpr; getNameAsString getScope ()Ljava/util/Optional; java/util/Optional toString equals (Ljava/lang/Object;)Z checkClassFields getInstanceType 
getComment 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String; checkParentClass TextReader/TTS getInstance ()LTextReader/TTS; read )(LcodeCompletion/CTSCompletionProvider;)V e(Lorg/netbeans/spi/editor/completion/support/AsyncCompletionQuery;Ljavax/swing/text/JTextComponent;)V java/lang/System out Ljava/io/PrintStream; java/io/PrintStream println (II)Ljava/lang/String; java/lang/Character isWhitespace (C)Z getParagraphElement (I)Ljavax/swing/text/Element; getStartOffset getEndOffset charAt (I)C append -(Ljava/lang/String;)Ljava/lang/StringBuilder; (I)Ljava/lang/StringBuilder; 	getLength (Ljava/lang/String;I)V 	initCause ,(Ljava/lang/Throwable;)Ljava/lang/Throwable; ! 8 9  :     < =  >   /     *� �    ?        @        A B    C D  >  �  
  ;,� � N,� � � 
:� 	�         	   �   �     2          *-,� 
� :::::� � �� :		� :	� � :� � 1� � :� :� O� � :� :� 9� � � � :� :� � W� :� :� � � 2� Y� Y*� ,� ��  "� #� �  $� #� �  %� #� 
:� 	�      2 &/2 &  ?   � *     !  $  "  #  ' L ) W * [ + _ , c - g / o 0 v 1 } 2 � 4 � 5 � 6 � 7 � 8 � 9 � : � ; � = � > � ? � @ � B � C � D � F � G  K n o q$ r' t/ y2 w4 x9 { @   z    E F  v � G H 	 W � I J  [ � K J  _ � L J  c � M J  g � N J 4  E O   ; A B    ; P Q   ; R S  3 T U  V   W �   8 W   2� r 
 8 W  X X X X X Y  � 
  8 W   

B & Z     [    \ ]  >   �     +� N*-� 6-d� ' :�    ?         �  �  � @   >     A B      T ^     _ Q    ` U    a Q    I J  b     &  c d  >   �     +�=��� +4>� (� �����    ?       �  �  �  �  �  �  � @   *   	 e f     A B      I g    h Q  V    �   i j  >  (     �+� ) N-� * 6`-� + � c+� ' � , � � N� E:� &Y� -Y� ./� 0� 12� 0`� 13� 0+� 4 � 1� 5� 6� 7� &������   / 5 &  ?   2    �  �  �  � / � 2 � 5 � 7 � ^ � p � w � } � @   >  7 @ E O    � A B     � T U    � k Q   x l m   p n Q  V    �  o!B &� A b     &  p q  >   @     �    ?       � @         A B      R S     r J  Z     [   s t  >   E     *+� �    ?        @         u B      v U     w Q  b     & x y  >   :     *+� �    ?        @        u B      v g   z    { Z     |  }s ~ c � ;   
        