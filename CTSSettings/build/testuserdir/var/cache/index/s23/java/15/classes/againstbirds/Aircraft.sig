����   3 �
 � �
 � �
  �	 C �	 C �	 C �	 C �
 C �
 C �
 � �
 � �	 � � �
 � � � �
 % � c � c � � �
  � �
 � � �
  � �
  �
 � �
 � �
  � � �  �
 C �
 C � �
 % �  �
 % �
 % �
 % �
 % � �
 C �
 C � � � � �
 2 � �
 C �
 4 �
 C � �
 C �
 � � � ; �
 C � � �
  � � � � healthLevel I numberOfBullets cureKit infected Z <init> ()V Code LineNumberTable LocalVariableTable this Lagainstbirds/Aircraft; move (C)V c C StackMapTable throwBullet (CLjava/util/ArrayList;)V item Lagainstbirds/Item; i choose itemList Ljava/util/ArrayList; nearest max destroyList LocalVariableTypeTable *Ljava/util/ArrayList<Lagainstbirds/Item;>; � 	Signature .(CLjava/util/ArrayList<Lagainstbirds/Item;>;)V aircraftDestroyedAnObject (Lagainstbirds/Item;)V a Lagainstbirds/Armory; p Lagainstbirds/Pharmacy; 	collusion (Ljava/util/ArrayList;)V healthConstant collusionList -(Ljava/util/ArrayList<Lagainstbirds/Item;>;)V checkCollusion +(Lagainstbirds/Item;Ljava/util/ArrayList;)I enemy cList @(Lagainstbirds/Item;Ljava/util/ArrayList<Lagainstbirds/Item;>;)I toString ()Ljava/lang/String; #org.netbeans.SourceLevelAnnotations Ljava/lang/Override; getHealthLevel ()I setHealthLevel (I)V getNumberOfBullets setNumberOfBullets 
getCureKit 
setCureKit 
isInfected ()Z setInfected (Z)V 
SourceFile Aircraft.java � � { � { J � D E F E G E H I f g � � � { � } � � � This should not be printed. � � � You reached the left border. You reached the right border. � � � � � � againstbirds/Item againstbirds/Shootable againstbirds/FlyingObjects � { java/lang/StringBuilder J K YOU HIT THE  � � � � � � � w v w againstbirds/GroundObjects You skipped that shoot. � K ~ {  } java/util/ArrayList � � � � � { � � � � againstbirds/Chicken z { | } againstbirds/Egg againstbirds/Duck againstbirds/BirdNest againstbirds/Armory againstbirds/Pharmacy � { � { � } againstbirds/Enemies q r � � againstbirds/Infectable H � � � /You got infection and you do not have cure kit. Your report:
Health Level:  � � 
Number Of Bullets: 
Number Of Cure Kits:  againstbirds/Aircraft java/util/Iterator againstbirds/Location2D getDimX getDimY (II)V getLocation ()Lagainstbirds/Location2D; getX setX java/lang/System out Ljava/io/PrintStream; java/io/PrintStream println (Ljava/lang/String;)V iterator ()Ljava/util/Iterator; hasNext next ()Ljava/lang/Object; getY append -(Ljava/lang/String;)Ljava/lang/StringBuilder; java/lang/Object getClass ()Ljava/lang/Class; java/lang/Class getSimpleName shooted 	toDestroy add (Ljava/lang/Object;)Z size get (I)Ljava/lang/Object; remove getNumberOfCureKits equals (Lagainstbirds/Location2D;)Z (I)Ljava/lang/StringBuilder; ! C      D E    F E    G E    H I     J K  L   x     2*� ld� d� *� *
� *� *� **� **� �    M   "           "  '  ,  1  N       2 O P    Q R  L       ��     Q      L   +   R   >   l   +   r   >*� 	*� 	� 
d� � *� 	*� 	� 
`� � � � *� 	� 
� � � *� 	� � $*� 	� 
� � � � *� 	� d� �    M   6     ,  <  ? " O # R % Z ) d * l + w , � - � . � 0 N       � O P     � S T  U    ,   V W  L  �    |N6,� :�  � ��  � :� � �� � 
*� 	� 
� �U� 	u� H� � �� � � �� � 6� � Y� � � � � � � N� kD� 	d� H�  � W� � � J� � 6� � Y� � � � � � � N� S� 	s� � !� � ��-� � -� � " **� #d� $� %Y� &:,� :�  � 5�  � :� � � � ' � *� � (W���6� )� ,� *� +W����    M   � #   6  7  8 ! 9 ) : ; ; G < O > \ ? f @ � A � H � I � K � L � M � N � U � W � X � c � e � f g l m6 n> oK pQ qY u\ wi xu w{ z N   \ 	 ! � X Y 6 # X Y _  Z E   | O P    | [ T   | \ ]  z ^ Y  w _ E  b ` ]  a      | \ b  b ` b  U   5 �   c� ; � D� D� 
� �  % c8� � �  d    e  f g  L       ~+� ,� **� -`� .� k+� /� **� -`� .� V+� 0� **� -h� .� B+� 1� � 8+� 2� +� 2M**� #,� 3`� $� +� 4� +� 4M**� 5,� 6`� 7�    M   :    �  �  �  � * � 1 � > � H � O � T � a � k � p � } � N   *  T  h i  p  j k    ~ O P     ~ X Y  U    	  l m  L       a=� %Y� &N+� :�  � "�  � :� 8� *-� 9=���**� -d� .6-� )� +-� *� +W����    M   .    �  � 
 � & � . � 6 � 9 � C � O � Z � ` � N   >  &  X Y  F  Z E    a O P     a \ ]   _ n E  
 W o ]  a       a \ b  
 W o b  U    �  % c%� � �  d    p  q r  L   �     V*� 	+� � :� I,+� (W+� ;� 9+� ;� < � +*� =*� 5� *� =**� 5d� 7�� >� 
����    M   :    �  �  �  � ' � , � 3 � 8 � B � D � L � O � Q � T � N        V O P     V s Y    V t ]  a       V t b  U    � D
 d    u  v w  L   Y     /� Y� ?� *� � @A� *� � @B� *� � @� �    M       � N       / O P   x     y    z {  L   /     *� �    M       � N        O P    | }  L   >     *� �    M   
    �  � N        O P      D E   ~ {  L   /     *� �    M       � N        O P     }  L   >     *� �    M   
    �  � N        O P      F E   � {  L   /     *� �    M       � N        O P    � }  L   >     *� �    M   
    �  � N        O P      G E   � �  L   /     *� �    M       � N        O P    � �  L   >     *� �    M   
     N        O P      H I   �    �