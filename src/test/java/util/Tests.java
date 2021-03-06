/**
 *   Copyright 2018 Pascal Ammon, Gabriel Wyss
 * 
 * 	 Implementation eines anonymen Mobility Pricing Systems auf Basis eines Gruppensignaturschemas
 * 
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Quick Test, if all the methods of the group signature scheme work:
 * tested methods: generate, init, join, sign, verify and open
 */
package util;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gson.Gson;

import data.Tuple;
import demo.DemoManagerKey;
import demo.DemoPublicKey;
import demo.DemoSecretKey;
import demo.DemoSignature;
import interfaces.HashValue;
import keys.ManagerKey;
import keys.SecretKey;
import keys.PublicKey;
import requests.JoinRequest;
import responses.JoinResponse;
import util.Generator;

public class Tests {

	// This test is based on old keys
	@Ignore
	@Test
	public void testWithMobileKey() {
		String publicKeyJson = "{\"a\":16245119034589036116784337633315976053691684329940030352956166073152297154452013717521203280414234757209218211002300461908120138925216484592313900516469073324451436612516467877370495874373581790472138389788518731453523770090946048032159899882588943149095304896177969115104237910580400135087093817150227782624824670319365909980214232420724597321133915486946142465416325787060820305278280250672969783094773897966553848848186589731598911003271114535029935428272792174499828231321938730891167456854734871090875849830416181913104922311489609885542911390276000981717932536354835138479028267663562753520574345572037332936950,\"bigF\":10220038898042125668235576516123153903275983181955220206868559237286975883023383377946009547173424680224250618611462095580534353073821643955140970867341889525986836505119207764119140478403362323381813048581684801382342668603425201988323614369665353352794045875696581350919563382330974869682727052629971648812580980395742009227134819482002054552980943915339688018074100788130046808577239746473322601974254140198546488004661673109862561308509786196740033268146735109228995305163354008330832514283995242873314747735136669626146141373938689219238270070129947576440877597431024259033638899832097356900755721255652528995604,\"bigG\":2447698333600378528887206612472040427237918852570770721027346729928950787045106879315137854100331928705985088319242524813117884490413097414486968010528307353795821297500957230542746475769927543689086173049553277148312681270009959506660458302884425926983431381149198117703237815528221843013782894039189793023389098714975020880968943011714137668126405831654078959151391382925085894133855583440835455837425681900208996012718410424112170019687085258868416115462129700546885650864684031642917055953271707095828249254810638447106343383063897982157523274240861220966092757212914070393841760880726375595332229900197824688108,\"bigH\":2122814073991038022987557264037095212323399773851530827776835207481882813455351569524574256436496274267717544230603919278386521105263556834990300946292894622462819890066703024101795853936190155101550912380380174508430856293362803563390568663527672974598596797893984610135316817456186388542767913640396825205998259723104833572360637033246769774229508048711271021942590509738494913513189524183446064781410350391737223473922832773478488976658502141566087682252533021129288721222072106110311790142360851986691376194810815729491909094241079196179704603302349550356554014275521498382785322594171067330088531254921261347581,\"bigP\":17927509024298591444786439139747883546432179583716533463680777529853829753548178979478032871603072350600371540978775388583772112669950654874089759667260279639657424644983032391219883344938326837448356777529921810408012665397454383422870960138606974868792850790122905187390706423208357535755347662255773959721694351126257325331550581039669318775506235304329109116705163776388404270545948041192770337236497514250384090065170329146088589409550240890815204768536253464273448471057153324193729663921510659882616093585538604240860856415039349465021207117142844829352996091900697245403412901182503489010075912991018303481551,\"bigQ\":4310698091246552964856210435205842790725556522275337116111604177745554138750015133223,\"g\":7305926203332403786606510015617968951726414641327975416273191960508920671964714359967307319673809207616372757865653693820243831928552524018456364624160730788984195175706548346903526449617833025256783648024769321951242751987084315606794966392491562741678327322311232942552338294086954254757028438573989562168596149144387348710333345393331628891173029961160348098279581977023984701046201608404316260770508409186913217207393685711468999857652839144058196313315299326516560474131799042361409553461083037645439812557594576962702384421872394325966038141689470228897980443291792258708884289738397178834795283203986592228611,\"h\":16834494404424358302335694104559500657076519704642141083095415010744494338837639449907824455841247770893335182060835605705054067682183408453613521267617504096586947078890808490380274878437568742555906370917545631052716984583199152763612925156159784846987432984776910783263869934621801538047070439455356287220122892140970801224739386037946095070352353390315493766333689502999683652499808572948416656360589891778731421113835654871176745150491374550315493671348661350778309905751686848567757296359341970952199259954288758888512663107663854479043047549060667846885899395646945406168701267168109651776943292153999801983474,\"n\":19190931471205695953893359730987032527310160548440299366149754381626659431938466062573654600143196090965356248742984440500620254161173793618869972273748048716180746745448950199359584754271833758310587810132210414912139262823772060304302713787422039834359950195783172061072391123735197576299987239640304328376863525522985633408044221596427505724873942579431353408371461802428440608535515344867054103877486932002576256066631150430145614768042812764613959957400815551161944991571437644331661126386541232478613563445659068998402630518861775452927944785497703268379732143406732314311747885501629608735431539908755749640271,\"w\":17766453458642641409795475678741238116257337033862794935240099184119501126653938602069311047459094294293328263976718162750734223555316884403848770314815797037136610059354767003189884330880808060763454176345107991556094306608877804328010558733504692212883949615195683119138802527008188670167454532918784330697746177924011296165331761611057518996475719547678877329634042912875563947724664809394807846998222329380593925895917208504843550060399033138887041717072251000926668868705137666875688866603415107263802380065955012529783444193904569606704981499552401836238468239919585307284986919924640887414313279457275728187206}";
		String secureKeyJson = "{\"bigE\":26187124863169134960105517574620793217733136368344518315866330944769070371237396439066160738607233257207093473020480568073738052367084061587885688606989,\"bigY\":8185489164842584778901019752630972529331201859184687551184859735694747525473540420564084614604209029620991679495204611705385094102021895103770279202550081208877122897316259295699801796750178223128063362048404561949237358524478109544843302268565375511975429280876833765943812583368064187670797525037485143319535885528025844836549098243865678584123730954966773958017387254811624277805439946809251166908794720200466682614196314804752632879220069205664702864827090335416947082385906908405578878196431086019059240350437246304645459131200264714494005560064816219288992103606114881162420002713987112815610855609465818583286,\"commitment\":18926145367804048095524902631993185213270628984295563630118516153706314159082552522729523492370003016668230347206920426655403104079289389646640302847958318769457415531104821719355566105757491868407560672280193980929702100321077623292993513073780131657472709519216980709345064466247586958021169278905706989540947304155549902668110895902973583273784684958360231405252098643326677952012991387576990869640254110860792075164270544077750737443419777014347029838566931893776860573769625013748082772372261232035529159587146350304531136217566377877826006706759232566831458498254180843076986233068426019437643342541688516005743,\"e\":917161257467891981,\"r\":31849946681755687862055379693643225136474068922762619532462950933050193020803141833229144026449222234141306113020057188697690992858275878938428061192795266583538745373589951439965001829708399933864931524787275645330642464904824736104839919182833855584509569839606158810977710360007049202590432702486023433111177921345859353576738951169308198216243684898279001058569427443736328321350326146601430140731166977512680267804467844231884339020026528862790893839630743692205084509430077031496922231507578994892789083717145845305113159875438123978458631897572437861820248916823422809368464971273654092603812808419364919633538,\"w\":17380136391037358263291322873855969647347970383017406768339656179093225130047763994831590265665391514406936564026227847283492825293272156769857650080847596367677296150500907139940810902875923052427125214101935619997724522690688115534192960027023032116933148221383758800345014919444457392622379791740321985744482502707624484780319271579177859113224607979880230340396678288692140711182151332460517459248170146270157777681849595707552731035149679968534805224176591396487251577473498664569290947573013920008862867082941741385303791681836831495079827267447909706961081181410218513879206855121225922003953158679336838299877,\"x\":16928859992250217639015630961560850398244543721136241218905247888747950594012659836423503129261880508952996070944476482549750889637445101618963202260889402021708077175338788984203448516018004720910956307976817723160620273830531244173970203269633269285064572571583138198664753038686473348872329720243981067071678190038749421664014479096404309218903432732487753313452052527064723710167729658468117931293744033059431161868173682169805790296723748725473419481308031119433572685414928770200005698983068861593740863753443056137760851436225878766985514554714045982716332392478437109088671561525191472994851054143191953536946,\"y\":16867640097447763129411211724432341152202733379330602969803925732613584855728295705638022243328531117934640517353232700500975592513386459465303813802391915545933849179174254572236192502976869391088717053811522782813172175777524613321048219521145325821786576550216275039134290819998376113095801774735441135448960356331867159807681267696561237056533267679091637711134356373475310272791200190831742049136006047033399433295673369468587691013315258487666706148022753434739237143449904778639813477801138276582603011011763052763318376760051680511179726818046027122251487065642236238020237218050207250940331552884455528604337}";

		Gson gson = new Gson();

		PublicKey publicKey = gson.fromJson(publicKeyJson, DemoPublicKey.class);
		SecretKey memberKey = gson.fromJson(secureKeyJson, DemoSecretKey.class);

		Tuple a = new Tuple();
		Date d = new Date();

		a.setLatitude(new BigDecimal("9").setScale(10, RoundingMode.HALF_UP));
		a.setLongitude(new BigDecimal("8").setScale(10, RoundingMode.HALF_UP));
		a.setCreated(d);

		byte[] testmessage = HashHelper.getHash(a);

		DemoSignature signature = new DemoSignature();
		SignHelper.sign(memberKey, publicKey, testmessage, signature);

		assertTrue(VerifyHelper.verify(publicKey, signature, HashHelper.getHash(a)));

	}

	class VInvoiceItems {
		@HashValue
		private HashMap<String, Integer> items;

		public HashMap<String, Integer> getItems() {
			return items;
		}

		public void setItems(HashMap<String, Integer> items) {
			this.items = items;
		}

	}

	@Test
	public void hashTest() {

		//test if equal input leads to same hash
		HashMap<String, Integer> items = new HashMap<>();
		items.put("testd", 2);
		items.put("test", 1);
		items.put("testa", 2);
		items.put("testb", 3);
		VInvoiceItems vInvoiceItems = new VInvoiceItems();
		vInvoiceItems.setItems(items);
		byte[] itemsHash1 = HashHelper.getHash(vInvoiceItems);
		byte[] itemsHash2 = HashHelper.getHash(vInvoiceItems);
		String hash1 = Base64.getEncoder().encodeToString(itemsHash1);
		String hash2 = Base64.getEncoder().encodeToString(itemsHash2);	
		System.out.println("should be equal: " + hash1 + " " + hash2);
		assertTrue(hash1.equals(hash2));
		
		//test if different input leads to different hash
		HashMap<String, Integer> items2 = new HashMap<>();
		items2.put("das", 1337);
		items2.put("ist", 666);
		items2.put("ein", 2);
		items2.put("super", 3);
		items2.put("test", 3);
		VInvoiceItems vInvoiceItems2 = new VInvoiceItems();
		vInvoiceItems.setItems(items2);
		byte[] itemsHash3 = HashHelper.getHash(vInvoiceItems2);
		String hash3 = Base64.getEncoder().encodeToString(itemsHash3);
		System.out.println("should not be equal: " + hash2 + " " + hash3);
		assertTrue(!hash2.equals(hash3));
		
	}

	@Test
	public void testSignAndVerify() {

		PublicKey publicKey = new DemoPublicKey();
		ManagerKey managerKey = new DemoManagerKey();

		Generator.generate(publicKey, managerKey);

		SecretKey memberKey = new DemoSecretKey();
		JoinHelper.init(publicKey, memberKey);

		JoinRequest joinRequest = new JoinRequest(memberKey);

		JoinResponse joinResponse = JoinHelper.join(publicKey, managerKey, joinRequest);

		memberKey.maintainResponse(joinResponse);

		Tuple a = new Tuple();
		Date d = new Date();

		a.setLatitude(new BigDecimal("9").setScale(10, RoundingMode.HALF_UP));
		a.setLongitude(new BigDecimal("8").setScale(10, RoundingMode.HALF_UP));
		a.setCreated(d);

		byte[] testmessage = HashHelper.getHash(a);

		a.setLongitude(new BigDecimal("10").setScale(10, RoundingMode.HALF_UP));
		byte[] testmessage2 = HashHelper.getHash(a);

		DemoSignature signature = new DemoSignature();
		SignHelper.sign(memberKey, publicKey, testmessage, signature);

		System.out.println("sig:" + Arrays.toString(HashHelper.getHash(signature)));

		DemoSignature signature2 = new DemoSignature();

		SignHelper.sign(memberKey, publicKey, testmessage, signature2);
		System.out.println("sig2:" + Arrays.toString(HashHelper.getHash(signature2)));

		Boolean res = VerifyHelper.verify(publicKey, signature, testmessage);
		assertTrue(res);

		Boolean res2 = VerifyHelper.verify(publicKey, signature, HashHelper.getHash(a));
		assertTrue(res);
	}

	@Test
	public void testKeyProperties() {
		PublicKey publicKey = new DemoPublicKey();
		ManagerKey managerKey = new DemoManagerKey();
		Generator.generate(publicKey, managerKey);

		// print the lengthof all the key components

		System.out.println("public key a: " + publicKey.getA().bitLength());
		System.out.println("public key g: " + publicKey.getG().bitLength());
		System.out.println("public key h: " + publicKey.getH().bitLength());
		System.out.println("public key n: " + publicKey.getN().bitLength());
		System.out.println("public key w: " + publicKey.getW().bitLength());
		System.out.println("public key F: " + publicKey.getBigF().bitLength());
		System.out.println("public key G: " + publicKey.getBigG().bitLength());
		System.out.println("public key H: " + publicKey.getBigH().bitLength());
		System.out.println("public key P: " + publicKey.getBigP().bitLength());
		System.out.println("public key Q: " + publicKey.getBigQ().bitLength());

		System.out.println("manager key  p:" + managerKey.getP().bitLength());
		System.out.println("manager key  q:" + managerKey.getQ().bitLength());
		System.out.println("manager key  Xg:" + managerKey.getXg().bitLength());

		// check if keys are null
		assertTrue(publicKey.getA() != null);
		assertTrue(publicKey.getG() != null);
		assertTrue(publicKey.getH() != null);
		assertTrue(publicKey.getN() != null);
		assertTrue(publicKey.getW() != null);
		assertTrue(publicKey.getBigF() != null);
		assertTrue(publicKey.getBigG() != null);
		assertTrue(publicKey.getBigP() != null);
		assertTrue(publicKey.getBigQ() != null);
		assertTrue(managerKey.getP() != null);
		assertTrue(managerKey.getQ() != null);
		assertTrue(managerKey.getXg() != null);

		// check the key length
		assertTrue(publicKey.getA().bitLength() == 2048);
		assertTrue(publicKey.getG().bitLength() > 2000);
		assertTrue(publicKey.getH().bitLength() == 2048);
		assertTrue(publicKey.getN().bitLength() == 2048);
		assertTrue(publicKey.getW().bitLength() == 2048);
		assertTrue(publicKey.getBigF().bitLength() > 2000);
		assertTrue(publicKey.getBigG().bitLength() > 2000);
		assertTrue(publicKey.getBigP().bitLength() > 2000);
		assertTrue(publicKey.getBigQ().bitLength() == 378);
		assertTrue(managerKey.getP().bitLength() == 1024);
		assertTrue(managerKey.getQ().bitLength() == 1024);
		assertTrue(managerKey.getXg().bitLength() == 378);
	}

	@Test
	public void testOpen() {
		PublicKey publicKey = new DemoPublicKey();
		ManagerKey managerKey = new DemoManagerKey();

		Generator.generate(publicKey, managerKey);

		// SmartPhone A
		SecretKey memberKeyA = new DemoSecretKey();
		JoinHelper.init(publicKey, memberKeyA);
		// System.out.println("member a E: " +memberKeyA.getBigE().bitLength());
		System.out.println("member a Y: " + memberKeyA.getBigY().bitLength());
		System.out.println("member a Commitment: " + memberKeyA.getCommitment().bitLength());
		// System.out.println("member a e: " +memberKeyA.getE().bitLength());
		System.out.println("member a r: " + memberKeyA.getR().bitLength());
		// System.out.println("member a w: " +memberKeyA.getW().bitLength());
		System.out.println("member a x: " + memberKeyA.getX().bitLength());
		// System.out.println("member a y: " +memberKeyA.getY().bitLength());

		// check if correct elements are set
		assertTrue(memberKeyA.getBigE() == null);
		assertTrue(memberKeyA.getE() == null);
		assertTrue(memberKeyA.getW() == null);
		assertTrue(memberKeyA.getY() == null);
		assertTrue(memberKeyA.getBigY() != null);
		assertTrue(memberKeyA.getCommitment() != null);
		assertTrue(memberKeyA.getR() != null);
		assertTrue(memberKeyA.getX() != null);

		// check length
		assertTrue(memberKeyA.getBigY().bitLength() > 2000);
		assertTrue(memberKeyA.getCommitment().bitLength() > 2000);
		assertTrue(memberKeyA.getR().bitLength() == 2048);
		assertTrue(memberKeyA.getX().bitLength() == 2048);

		// SmartPhone B
		SecretKey memberKeyB = new DemoSecretKey();
		JoinHelper.init(publicKey, memberKeyB);

		// System.out.println("member b E: " +memberKeyB.getBigE().bitLength());
		System.out.println("member b Y: " + memberKeyB.getBigY().bitLength());
		System.out.println("member b Commitment: " + memberKeyB.getCommitment().bitLength());
		// System.out.println("member b e: " +memberKeyB.getE().bitLength());
		System.out.println("member b r: " + memberKeyB.getR().bitLength());
		// System.out.println("member b w: " +memberKeyB.getW().bitLength());
		System.out.println("member b x: " + memberKeyB.getX().bitLength());
		// System.out.println("member b y: " +memberKeyB.getY().bitLength());

		// check if correct elements are set
		assertTrue(memberKeyB.getBigE() == null);
		assertTrue(memberKeyB.getE() == null);
		assertTrue(memberKeyB.getW() == null);
		assertTrue(memberKeyB.getY() == null);
		assertTrue(memberKeyB.getBigY() != null);
		assertTrue(memberKeyB.getCommitment() != null);
		assertTrue(memberKeyB.getR() != null);
		assertTrue(memberKeyB.getX() != null);

		// check member key element length
		assertTrue(memberKeyB.getBigY().bitLength() > 2000);
		assertTrue(memberKeyB.getCommitment().bitLength() > 2000);
		assertTrue(memberKeyB.getR().bitLength() == 2048);
		assertTrue(memberKeyB.getX().bitLength() == 2048);

		JoinRequest joinRequestA = new JoinRequest(memberKeyA);
		JoinRequest joinRequestB = new JoinRequest(memberKeyB);

		// check join response
		JoinResponse joinResponseA = JoinHelper.join(publicKey, managerKey, joinRequestA);
		System.out.println("join response a e: " + joinResponseA.getE().bitLength());
		System.out.println("join response a Ei: " + joinResponseA.getEi().bitLength());
		System.out.println("join response a ri: " + joinResponseA.getRi().bitLength());
		System.out.println("join response a wi: " + joinResponseA.getWi().bitLength());
		System.out.println("join response a Yi: " + joinResponseA.getYi().bitLength());
		assertTrue(joinResponseA.getE() != null);
		assertTrue(joinResponseA.getEi() != null);
		assertTrue(joinResponseA.getRi() != null);
		assertTrue(joinResponseA.getWi() != null);
		assertTrue(joinResponseA.getYi() != null);
		assertTrue(joinResponseA.getE().bitLength() == 60);
		assertTrue(joinResponseA.getEi().bitLength() == 696);
		assertTrue(joinResponseA.getRi().bitLength() > 50);
		assertTrue(joinResponseA.getWi().bitLength() > 2000);
		assertTrue(joinResponseA.getYi().bitLength() > 2000);

		JoinResponse joinResponseB = JoinHelper.join(publicKey, managerKey, joinRequestB);
		System.out.println("join response b e: " + joinResponseB.getE().bitLength());
		System.out.println("join response b Ei: " + joinResponseB.getEi().bitLength());
		System.out.println("join response b ri: " + joinResponseB.getRi().bitLength());
		System.out.println("join response b wi: " + joinResponseB.getWi().bitLength());
		System.out.println("join response b Yi: " + joinResponseB.getYi().bitLength());
		assertTrue(joinResponseB.getE() != null);
		assertTrue(joinResponseB.getEi() != null);
		assertTrue(joinResponseB.getRi() != null);
		assertTrue(joinResponseB.getWi() != null);
		assertTrue(joinResponseB.getYi() != null);
		assertTrue(joinResponseB.getE().bitLength() == 60);
		assertTrue(joinResponseB.getEi().bitLength() == 696);
		assertTrue(joinResponseB.getRi().bitLength() > 50);
		assertTrue(joinResponseB.getWi().bitLength() > 2000);
		assertTrue(joinResponseB.getYi().bitLength() > 2000);

		// check resulting member key
		memberKeyA.maintainResponse(joinResponseA);
		System.out.println("member a E: " + memberKeyA.getBigE().bitLength());
		System.out.println("member a Y: " + memberKeyA.getBigY().bitLength());
		System.out.println("member a Commitment: " + memberKeyA.getCommitment().bitLength());
		System.out.println("member a e: " + memberKeyA.getE().bitLength());
		System.out.println("member a r: " + memberKeyA.getR().bitLength());
		System.out.println("member a w: " + memberKeyA.getW().bitLength());
		System.out.println("member a x: " + memberKeyA.getX().bitLength());
		System.out.println("member a y: " + memberKeyA.getY().bitLength());
		assertTrue(memberKeyA.getBigE() != null);
		assertTrue(memberKeyA.getBigY() != null);
		assertTrue(memberKeyA.getCommitment() != null);
		assertTrue(memberKeyA.getE() != null);
		assertTrue(memberKeyA.getR() != null);
		assertTrue(memberKeyA.getW() != null);
		assertTrue(memberKeyA.getX() != null);
		assertTrue(memberKeyA.getY() != null);
		assertTrue(memberKeyA.getBigE().bitLength() == 696);
		assertTrue(memberKeyA.getBigY().bitLength() > 2000);
		assertTrue(memberKeyA.getCommitment().bitLength() > 2000);
		assertTrue(memberKeyA.getE().bitLength() == 60);
		assertTrue(memberKeyA.getR().bitLength() == 2048);
		assertTrue(memberKeyA.getW().bitLength() > 2000);
		assertTrue(memberKeyA.getX().bitLength() == 2048);
		assertTrue(memberKeyA.getY().bitLength() > 2000);

		memberKeyB.maintainResponse(joinResponseB);
		System.out.println("member b E: " + memberKeyB.getBigE().bitLength());
		System.out.println("member b Y: " + memberKeyB.getBigY().bitLength());
		System.out.println("member b Commitment: " + memberKeyB.getCommitment().bitLength());
		System.out.println("member b e: " + memberKeyB.getE().bitLength());
		System.out.println("member b r: " + memberKeyB.getR().bitLength());
		System.out.println("member b w: " + memberKeyB.getW().bitLength());
		System.out.println("member b x: " + memberKeyB.getX().bitLength());
		System.out.println("member b y: " + memberKeyB.getY().bitLength());
		assertTrue(memberKeyB.getBigE() != null);
		assertTrue(memberKeyB.getBigY() != null);
		assertTrue(memberKeyB.getCommitment() != null);
		assertTrue(memberKeyB.getE() != null);
		assertTrue(memberKeyB.getR() != null);
		assertTrue(memberKeyB.getW() != null);
		assertTrue(memberKeyB.getX() != null);
		assertTrue(memberKeyB.getY() != null);
		assertTrue(memberKeyB.getBigE().bitLength() == 696);
		assertTrue(memberKeyB.getBigY().bitLength() > 2000);
		assertTrue(memberKeyB.getCommitment().bitLength() > 2000);
		assertTrue(memberKeyB.getE().bitLength() == 60);
		assertTrue(memberKeyB.getR().bitLength() == 2048);
		assertTrue(memberKeyB.getW().bitLength() > 2000);
		assertTrue(memberKeyB.getX().bitLength() == 2048);
		assertTrue(memberKeyB.getY().bitLength() > 2000);

		byte[] testmessage = new BigInteger("1990").toByteArray();

		// check signature creation and values
		DemoSignature signatureA = new DemoSignature();
		SignHelper.sign(memberKeyA, publicKey, testmessage, signatureA);
		System.out.println("signature a U1: " + signatureA.getBigU1().bitLength());
		System.out.println("signature a U2: " + signatureA.getBigU2().bitLength());
		System.out.println("signature a U3: " + signatureA.getBigU3().bitLength());
		System.out.println("signature a C: " + signatureA.getC().bitLength());
		System.out.println("signature a u: " + signatureA.getU().bitLength());
		System.out.println("signature a zR: " + signatureA.getZbigR().bitLength());
		System.out.println("signature a Ze: " + signatureA.getZe().bitLength());
		System.out.println("signature a Zr: " + signatureA.getZr().bitLength());
		System.out.println("signature a Zx: " + signatureA.getZx().bitLength());
		assertTrue(signatureA.getBigU1() != null);
		assertTrue(signatureA.getBigU2() != null);
		assertTrue(signatureA.getBigU3() != null);
		assertTrue(signatureA.getC() != null);
		assertTrue(signatureA.getU() != null);
		assertTrue(signatureA.getZbigR() != null);
		assertTrue(signatureA.getZe() != null);
		assertTrue(signatureA.getZr() != null);
		assertTrue(signatureA.getZx() != null);
		assertTrue(signatureA.getBigU1().bitLength() > 2000);
		assertTrue(signatureA.getBigU2().bitLength() > 2000);
		assertTrue(signatureA.getBigU3().bitLength() > 2000);
		assertTrue(signatureA.getC().bitLength() > 250);
		assertTrue(signatureA.getU().bitLength() > 2000);
		assertTrue(signatureA.getZbigR().bitLength() == 378);
		assertTrue(signatureA.getZe().bitLength() == 376);
		assertTrue(signatureA.getZr().bitLength() > 2300);
		assertTrue(signatureA.getZx().bitLength() > 2300);

		DemoSignature signatureB = new DemoSignature();
		SignHelper.sign(memberKeyB, publicKey, testmessage, signatureB);
		System.out.println("signature b U1: " + signatureB.getBigU1().bitLength());
		System.out.println("signature b U2: " + signatureB.getBigU2().bitLength());
		System.out.println("signature b U3: " + signatureB.getBigU3().bitLength());
		System.out.println("signature b C: " + signatureB.getC().bitLength());
		System.out.println("signature b u: " + signatureB.getU().bitLength());
		System.out.println("signature b zR: " + signatureB.getZbigR().bitLength());
		System.out.println("signature b Ze: " + signatureB.getZe().bitLength());
		System.out.println("signature b Zr: " + signatureB.getZr().bitLength());
		System.out.println("signature b Zx: " + signatureB.getZx().bitLength());
		assertTrue(signatureB.getBigU1() != null);
		assertTrue(signatureB.getBigU2() != null);
		assertTrue(signatureB.getBigU3() != null);
		assertTrue(signatureB.getC() != null);
		assertTrue(signatureB.getU() != null);
		assertTrue(signatureB.getZbigR() != null);
		assertTrue(signatureB.getZe() != null);
		assertTrue(signatureB.getZr() != null);
		assertTrue(signatureB.getZx() != null);
		assertTrue(signatureB.getBigU1().bitLength() > 2000);
		assertTrue(signatureB.getBigU2().bitLength() > 2000);
		assertTrue(signatureB.getBigU3().bitLength() > 2000);
		assertTrue(signatureB.getC().bitLength() > 250);
		assertTrue(signatureB.getU().bitLength() > 2000);
		assertTrue(signatureB.getZbigR().bitLength() == 378);
		assertTrue(signatureB.getZe().bitLength() == 376);
		assertTrue(signatureB.getZr().bitLength() > 2300);
		assertTrue(signatureB.getZx().bitLength() > 2300);

		assertTrue(VerifyHelper.verify(publicKey, signatureA, testmessage));
		assertTrue(VerifyHelper.verify(publicKey, signatureB, testmessage));

		BigInteger[] yArr = new BigInteger[] { memberKeyA.getBigY(), memberKeyB.getBigY() };

		assertEquals(1, OpenHelper.open(publicKey, managerKey, testmessage, signatureB, yArr));
		assertEquals(0, OpenHelper.open(publicKey, managerKey, testmessage, signatureA, yArr));

	}

}
