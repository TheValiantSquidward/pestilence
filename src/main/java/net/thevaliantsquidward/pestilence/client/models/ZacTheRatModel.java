package net.thevaliantsquidward.pestilence.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.thevaliantsquidward.pestilence.entities.ZacTheRat;

public class ZacTheRatModel<T extends ZacTheRat> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart head;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart mouth;
	private final ModelPart lower_mouth;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart left_foot;
	private final ModelPart right_leg;
	private final ModelPart right_foot;
	private final ModelPart tail;

	public ZacTheRatModel(ModelPart root) {
		this.root = root.getChild("root");
		this.head = this.root.getChild("head");
		this.left_ear = this.head.getChild("left_ear");
		this.right_ear = this.head.getChild("right_ear");
		this.mouth = this.head.getChild("mouth");
		this.lower_mouth = this.mouth.getChild("lower_mouth");
		this.body = this.root.getChild("body");
		this.left_arm = this.body.getChild("left_arm");
		this.right_arm = this.body.getChild("right_arm");
		this.left_leg = this.body.getChild("left_leg");
		this.left_foot = this.left_leg.getChild("left_foot");
		this.right_leg = this.body.getChild("right_leg");
		this.right_foot = this.right_leg.getChild("right_foot");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 13.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(28, 20).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -2.0F));

		PartDefinition left_ear = head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, -3.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -6.0F, 0.0F));

		PartDefinition right_ear = head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 42).mirror().addBox(-2.0F, -3.0F, -1.0F, 3.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, -6.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(40, 13).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 40).addBox(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.002F)), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition lower_mouth = mouth.addOrReplaceChild("lower_mouth", CubeListBuilder.create().texOffs(36, 40).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(1.0F, -4.0F, -2.0F));

		PartDefinition Body_r1 = body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -4.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 2.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(28, 32).addBox(0.0F, -7.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(17, 22).addBox(0.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 2.0F, 2.0F, 0.3491F, 0.0873F, 0.7854F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(28, 32).mirror().addBox(-12.0F, -7.0F, 0.0F, 12.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(17, 22).mirror().addBox(-3.0F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, 2.0F, 2.0F, 0.3491F, -0.0873F, -0.7854F));

		PartDefinition left_leg = body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(40, 5).addBox(0.0F, -1.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 8.0F, 4.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition left_foot = left_leg.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(24, 47).addBox(-1.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 5.0F, 0.0F));

		PartDefinition right_leg = body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(40, 5).mirror().addBox(-7.0F, -1.0F, 0.0F, 7.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-6.0F, 8.0F, 4.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition right_foot = right_leg.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(24, 47).mirror().addBox(-5.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 5.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 20).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(16, 34).addBox(-1.0F, -13.0F, 9.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-1.0F, -13.0F, 11.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(8, 42).addBox(-1.0F, -11.0F, 16.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.0F, 8.0F, -0.6981F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(ZacTheRat entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.root.xRot = headPitch / (180F / (float) Math.PI);
		this.root.yRot = netHeadYaw / (180F / (float) Math.PI);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}