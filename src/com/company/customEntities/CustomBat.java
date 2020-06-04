package com.company.customEntities;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftBat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.Bat;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Field;
import java.util.List;

public class CustomBat extends EntityBat {

    protected Field FIELD_JUMP = null;

    public CustomBat(World world) {
//        super(world);
//
        super(world);
        List goalB = (List) getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
        List goalC = (List) getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        List targetB = (List) getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        List targetC = (List) getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();

        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));

        if (FIELD_JUMP == null) {
            try {
                FIELD_JUMP = EntityLiving.class.getDeclaredField("aY");
                FIELD_JUMP.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object getPrivateField(String fieldName, Class clazz, Object object)
    {
        Field field;
        Object o = null;
        try
        {
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        }
        catch(NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return o;
    }

    @Override
    public void g(float f, float f1) {
        if (this.passenger != null && this.passenger instanceof EntityHuman) {
            this.lastYaw = this.yaw = this.passenger.yaw;
            this.pitch = this.passenger.pitch * 0.5F;
            this.setYawPitch(this.yaw, this.pitch);
            this.aK = this.aI = this.yaw;
            f = ((EntityLiving) this.passenger).aZ * 0.5F;
            f1 = ((EntityLiving) this.passenger).ba;

            if (f1 <= 0.0F) {
                f1 *= 0.25F;
            }
            try {
                if (FIELD_JUMP.getBoolean(this.passenger) && this.onGround) {
                    this.motY += 1F;
                    this.ai = true;
                    if (f1 > 0.0F) {
                        float f2 = MathHelper.sin(this.yaw * 3.141593F / 180.0F);
                        float f3 = MathHelper.cos(this.yaw * 3.141593F / 180.0F);

                        this.motX += -0.4F * f2 * ((EntityInsentient) this).br();
                        this.motZ += 0.4F * f3 * ((EntityInsentient) this).br();
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }

            this.S = 1.0F;
            this.aM = this.bI() * 0.1F;
            if (!this.world.isClientSide) {
                this.k((float) this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).getValue());
                super.g(f, f1);
            }

            this.aA = this.aB;
            double d0 = this.locX - this.lastX;
            double d1 = this.locZ - this.lastZ;
            float f4 = MathHelper.sqrt(d0 * d0 + d1 * d1) * 4.0F;
            if (f4 > 1.0F) {
                f4 = 1.0F;
            }

            this.aB += (f4 - this.aB) * 0.4F;
            this.aC += this.aB;
        } else {
            this.S = 0.5F;
            this.aM = 0.02F;
            super.g(f, f1);
        }
    }

    public static Bat spawn(Location location) {
        World mcWorld = ((CraftWorld) location.getWorld()).getHandle();

        CustomBat customEntity = new CustomBat(mcWorld);
        customEntity.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        ((CraftLivingEntity) customEntity.getBukkitEntity()).setRemoveWhenFarAway(false);

        mcWorld.addEntity(customEntity, CreatureSpawnEvent.SpawnReason.CUSTOM);

        return (CraftBat) customEntity.getBukkitEntity();
    }
}
