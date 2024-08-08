# illuminati-main
We will develop main project on this repo

Model
အခု project မှာ Model တစ်ခုတည်ဆောက်ချင်ရင် အောက်ပါအချက်တွေလိုက်နာရပါမယ်
1. တည်ဆောက်ချင်တဲ့ model ရဲ့ နာမည်ကိုသတ်မှတ်ထားတဲ့အချက်အတိုင်းလိုက်နာပြီးပေးရပါမယ်။ (Model နဲ့နာမည်ဟာသူ manage
   လုပ်မဲ့ entity class နဲ့ Model နဲ့ကို CamelCase သုံးပြီးရေးပေးရပါမယ်။
   ဥပမာ - UserModel
3. AbrstractModel ဆိုတဲ့ class ကို extends လုပ်ပြီး Type parameter အနေနဲ့ manage လုပ်မဲ့ Entity class ရဲ့ Type
   ကို ထည့်ပေးရပါမယ်။
   ဥပမာ - public class UserModel extends AbstractModel<User> {}
4. Relational operation တွေပါ‌ ရေးချင်တဲ့အခါမှာတော့ Relational ဆိုတဲ့ interface ကိုပါ implements
   လုပ်ရမှာဖြစ်ပါတယ်။
   ဥပမာ - public class UserModel extends AbstractModel<User> implements Relational {}

Controller
controller class တွေရေးသားမယ်ဆိုရင်တော့ အောက်ပါအချက်တွေလိုက်နာရပါမယ်
1. Controller ဆိုတဲ့ class ကို extends လုပ်ရပါမယ်။
2. Controller ကို extends လုပ်လိုက်တာနဲ့ view(req, resp, view) နဲ့ redirect(req, resp, path)
   ဆိုတဲ့ method တွေကို အသင့်အသုံးပြုနိုင်သွားမှာဖြစ်ပါတယ်။
3. view method မှာတော့ forward လုပ်ပေးချင်တဲ့ jsp file ကို နာမည်ကိုပေးရမှာဖြစ်ပါတယ် အနောက်က .jsp ဆိုတဲ့
   extension မပါပါ
4. redirect method မှာတော့ redirect လုပ်လိုတဲ့ url ရဲ့ absolute path ကိုပေးရမှာဖြစ်ပါတယ်။

Service
service တွေဟာ model တွေကိုအသုံးပြုမှာဖြစ်တဲ့အတွက် လိုအပ်တဲ့ Model object ဆောက်ယူပုံကို ပြောပြထားမှာဖြစ်ပါတယ်။
1. Model ကိုအသုံးပြုလိုလျှင် Model ဆိုတဲ့ interface ကိုပြင်ပေးထားမှာဖြစ်ပြီး object ကိုတော့ ModelFactory ကနေ
   ရယူလိုတဲ့ model type ကိုသတ်မှတ်ပြီးအလိုအလျောက်ထုတ်ယူနိုင်မှာဖြစ်ပါတယ်။
   
   ဥပမာ - Model<User> userModel = ModelFactory.getModel(User.class, connector);

   UserModel ကို ရယူလိုတဲ့အခါ Model ကို User ကို manage လုပ်ဖို့သုံးမယ်ဆိုပြီး Model<User> လို့
   declare လုပ်ပါတယ်။
   နောက်ပြီး ModelFactory ကနေ getModel ဆိုပြီး manage လုပ်မဲ့ Entity class ကို User.class လို့ထည့်ပေးပြီး
   ဒုတိယ argument အနေနဲ့တော့ connector ကိုပေးရပါတယ်။
   
