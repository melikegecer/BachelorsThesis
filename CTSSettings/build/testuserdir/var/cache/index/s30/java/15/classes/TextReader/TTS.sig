����   3 v
  F G	  H I
  F J
 K L
  M N
  O P
 K Q
 R S	  T
 R U	  V
 W X	  Y Z
  F
 W [
 W \
 W ]
 W ^
 W _ `
  a
  b
  [ c voiceManager %Lcom/sun/speech/freetts/VoiceManager; voice Lcom/sun/speech/freetts/Voice; 	voiceName Ljava/lang/String; instance LTextReader/TTS; <init> ()V Code LineNumberTable LocalVariableTable this 
mbrolaPath getInstance ()LTextReader/TTS; StackMapTable getStyle ()Ljava/lang/String; setStyle (Ljava/lang/String;)V style setSpeakingRate (F)V rate F getSpeakingRate ()F read words setVoiceName getVoiceName toString #org.netbeans.SourceLevelAnnotations Ljava/lang/Override; <clinit> 
SourceFile TTS.java ' ( 
mbrola_us1 # $ java/lang/StringBuilder user.dir d e f g h \release\modules\ext\mbrola\ @ 2 mbrola.base i j k . l    m n ! " o p ( % & TextReader/TTS 1 2 3 4 q 7 r ; s t    : ; g u java/lang/Object java/lang/System getProperty &(Ljava/lang/String;)Ljava/lang/String; append -(Ljava/lang/String;)Ljava/lang/StringBuilder; setProperty 8(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String; #com/sun/speech/freetts/VoiceManager '()Lcom/sun/speech/freetts/VoiceManager; getVoice 2(Ljava/lang/String;)Lcom/sun/speech/freetts/Voice; com/sun/speech/freetts/Voice allocate setRate getRate speak (Ljava/lang/String;)Z (F)Ljava/lang/StringBuilder; !             ! "    # $   
 % &     ' (  )   �     G*� *� � Y� � � 	� � 
L+� W*� � **� *� � � *� � �    *   "      
 
  "  )  0  ?  F  +       G , &   " % - $  	 . /  )   =      � � � Y� � � �    *            # 0      1 2  )   2     *� � �    *       ' +        , &    3 4  )   A     	*� +� �    *   
    +  , +       	 , &     	 5 $   6 7  )   A     	*� #� �    *   
    /  0 +       	 , &     	 8 9   : ;  )   2     *� � �    *       3 +        , &    < 4  )   B     
*� +� W�    *   
    : 	 ; +       
 , &     
 = $   > 4  )   N     **� +� � *+� �    *       A  B  C +        , &      # $   ? 2  )   /     *� �    *       I +        , &    @ 2  )   T     *� Y� *� � � *� � � *� � � 
�    *       N +       * , &   A     B    C (  )         � �    *         D    E