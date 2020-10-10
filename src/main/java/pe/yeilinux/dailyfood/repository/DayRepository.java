package pe.yeilinux.dailyfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.yeilinux.dailyfood.dto.DayDTO;
import pe.yeilinux.dailyfood.entity.DayEntity;

import javax.transaction.Transactional;

@Repository
public interface DayRepository extends JpaRepository<DayEntity,Long>{
    @Query("SELECT new pe.yeilinux.dailyfood.dto.DayDTO(day.dayId,user.userId,day.weekSchema) FROM DayEntity day INNER JOIN day.user user WHERE user.userId = ?1")
    DayDTO getDaySchemaByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE days SET user_id= ?2,week_schema= ?3 WHERE day_id= ?1",nativeQuery = true)
    void updateDaySchema(Long dayId,Long userId,String weekSchema);

    @Transactional
    @Modifying
    @Query(value = "UPDATE days SET week_schema= ?2 WHERE user_id= ?1",nativeQuery = true)
    void updateDaySchemaByUserId(Long userId,String weekSchema);
}
