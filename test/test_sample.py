def func(x):
    return x + 1

def test_answer():
    assert func(3) == 4
    
    
from unittest.mock import Mock, patch

class ProductionClass():
    def method(self):
        return 0

    def foo(self, value):
        return 2*value


@patch('blog.utils.ProductionClass.foo')
def test_patch(mock_foo):
    mock_foo.return_value = 'mocked return value'
    assert mock_foo() == 'mocked return value'


def test_patch_result():
    pc = ProductionClass()
    assert pc.foo(2) == 4
    
#mock은 사용자가 컨트롤 할 수 없는 외부에 의존적인 함수를 치환하는 방법으로 씁니다. 
#patch는 내부 프로그램을 로컬에서 잠시 다른 기능을 수행하도록 수정 / 런타임에만 영향을 준당
