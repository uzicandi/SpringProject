package oracle.java.bmw.dao;

import java.util.List;

import oracle.java.bmw.model.Member;
import oracle.java.bmw.model.Point;

public interface PointDao {
	int 	lastPoint(String memberId);
	int 	pointTotal(String memberId);
	List<Point> pointList(Point point);
	int		pointCharge(Point point);
	// 동범
	int		subtractionPoint(Point point);	//포인트 차감
}
